package com.avasiu.tests;

import com.avasiu.Encryptor;
import com.avasiu.PropertiesManager;
import com.avasiu.steps.serenity.EndUserSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class Facebook {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    private EndUserSteps steps;

    @Test
    public void test_fblogin_works()
    {
        steps.is_the_home_page();
        steps.tryLogin(PropertiesManager.getProperty("GithubUsername"),
                Encryptor.decrypt(PropertiesManager.getProperty("GithubPassword")),
                true);
    }

    @Test
    public void test_fblogin_fails()
    {
        steps.is_the_home_page();
        steps.tryLogin(PropertiesManager.getProperty("GithubUsername"),
                "FAKE",
                false);
    }

    @Test
    public void test_reposearch_works()
    {
        steps.is_the_home_page();
        steps.login(PropertiesManager.getProperty("GithubUsername"),
                Encryptor.decrypt(PropertiesManager.getProperty("GithubPassword")));
        steps.searchRepo(true);
    }

    @Test
    public void test_reposearch_fails()
    {
        steps.is_the_home_page();
        steps.login(PropertiesManager.getProperty("GithubUsername"),
                Encryptor.decrypt(PropertiesManager.getProperty("GithubPassword")));
        steps.searchRepo(false);
    }

    @Test
    public void test_createfile_works()
    {
        steps.is_the_home_page();
        steps.login(PropertiesManager.getProperty("GithubUsername"),
                Encryptor.decrypt(PropertiesManager.getProperty("GithubPassword")));
        steps.goToRepo(PropertiesManager.getProperty("TestRepo"));
        steps.CreateFile(true);
    }

    @Test
    public void test_createfile_fails()
    {
        steps.is_the_home_page();
        steps.login(PropertiesManager.getProperty("GithubUsername"),
                Encryptor.decrypt(PropertiesManager.getProperty("GithubPassword")));
        steps.goToRepo(PropertiesManager.getProperty("TestRepo"));
        steps.CreateFile(false);
    }
}