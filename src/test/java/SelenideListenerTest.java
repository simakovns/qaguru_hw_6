import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import pages.GithubWebSteps;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class SelenideListenerTest {

    private final String REPOSITORY = "simakovns/qaguru_hw_6";
    private final String ISSUENUMBER = "#1";

    @Test
    public void clearSelenideTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").submit();

        $(linkText(REPOSITORY)).click();
        $(partialLinkText("Issues")).click();
        $(withText(ISSUENUMBER)).should(Condition.visible);
    }

    @Test
    public void lambdaStepTest() {
        step("Open main github page", () -> {
            open("https://github.com");
        });

        step("Search for REPOSITORY: " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });

        step("Click link on REPOSITORY: " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Click Issues tab", () -> {
            $(partialLinkText("Issues")).click();
        });
        step("Check Issue NUMBER " + ISSUENUMBER, () -> {
            $(withText(ISSUENUMBER)).should(Condition.visible);
        });
    }

    @Test
    public void stepTest() {
        GithubWebSteps githubWebSteps = new GithubWebSteps();
        githubWebSteps.openMainPage();
        githubWebSteps.searchForRepository(REPOSITORY);
        githubWebSteps.openRepositoryPage(REPOSITORY);
        githubWebSteps.openIssuesTab();
        githubWebSteps.shouldSeeIssueWithNumber(ISSUENUMBER);
    }
}
