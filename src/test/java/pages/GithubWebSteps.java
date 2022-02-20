package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;


import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class GithubWebSteps {

    @Step("Open main github page")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Search for {repository}")
    public void searchForRepository(String repository) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repository);
        $(".header-search-input").submit();
    }

    @Step("Click link on {repository}")
    public void openRepositoryPage(String repository) {
        $(linkText(repository)).click();
    }

    @Step("Click Issues tab")
    public void openIssuesTab() {
        $(partialLinkText("Issues")).click();
    }

    @Step("Check Issue {text}")
    public void shouldSeeIssueWithText(String text) {
        $("#issue_1").shouldHave(Condition.text(text));
    }

    @Attachment(value = "Screenshot", type = "text/html", fileExtension = "html")
    public byte[] attachPageSource() {
        return WebDriverRunner.source().getBytes(StandardCharsets.UTF_8);
    }
}
