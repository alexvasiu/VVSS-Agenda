package com.avasiu.steps.serenity;

import com.avasiu.PropertiesManager;
import com.avasiu.pages.FacebookPage;
import net.thucydides.core.annotations.Step;
import org.apache.commons.lang3.tuple.Pair;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EndUserSteps {

    private FacebookPage facebookPage;

    @Step
    public void is_the_home_page() {
        facebookPage.open();
    }

    @Step
    public void tryLogin(String username, String password, boolean withSuccess)
    {
        login(username, password);
        if (withSuccess)
            assertThat(facebookPage.getStringPage(),
                    containsString(PropertiesManager.getProperty("StringToRecognizeLogin")));
        else
            assertThat(facebookPage.getStringPage(),
                    not(containsString(PropertiesManager.getProperty("StringToRecognizeLogin"))));
    }

    @Step
    public void login(String username, String password)
    {
        facebookPage.enterCredentials(username, password);
        facebookPage.login();
    }

    @Step
    public void searchRepo(boolean withSuccess)
    {
        if (withSuccess)
            assertThat(facebookPage.search(PropertiesManager.getProperty("SearchValid"), true),
                    containsString(PropertiesManager.getProperty("SearchValidExpected")));
        else
            assertThat(facebookPage.search(PropertiesManager.getProperty("SearchInvalid"), false), isEmptyOrNullString());
    }

    @Step
    public void goToRepo(String repo)
    {
        facebookPage.searchAndGo(repo);
    }

    @Step
    public void CreateFile(boolean withSuccess)
    {
        facebookPage.pressCreateFile();
        Pair<String, String> result = facebookPage.writeFile(withSuccess);
        if (withSuccess)
            assertThat(result.getKey(), containsString(result.getValue()));
        else
            assertThat(result.getKey(), not(containsString(result.getValue())));
    }
}