package com.avasiu.pages;

import com.avasiu.PropertiesManager;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.By;

@DefaultUrl("https://github.com/login")
public class FacebookPage extends PageObject {

    @FindBy(name="login")
    private WebElementFacade emailText;

    @FindBy(name="password")
    private WebElementFacade passwordText;

    @FindBy(name = "commit")
    private WebElementFacade loginBtn;

    public void enterCredentials(String username, String password)
    {
        emailText.type(username);
        passwordText.type(password);
    }

    public String search(String searchText, boolean ok) {
        find(By.id("dashboard-repos-filter-left")).type(searchText);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElementFacade searchResults = findAll(By.cssSelector("ul[data-filterable-for='dashboard-repos-filter-left'][data-filterable-type='substring']"))
                .get(1);
        if (searchResults.getAttribute("class").contains("filterable-empty"))
            return "";
        else
            return searchResults.getText();
    }

    public void searchAndGo(String repo)
    {
        getDriver().navigate().to(PropertiesManager.getProperty("GithubWebSite") +
                PropertiesManager.getProperty("GithubUsername") + "/" +
                repo);
    }

    public void pressCreateFile()
    {
        find(By.cssSelector("form[class='BtnGroup-parent']")).findBy(By.tagName("button")).click();
    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public Pair<String, String> writeFile(boolean ok)
    {
        String file = randomAlphaNumeric(30);
        if (ok) {
            find(By.name("filename")).type(file + ".txt");
            find(By.className("CodeMirror-code")).type("# File: " + file);
            find(By.cssSelector("button[data-tab='preview']")).click();
        }

        try {
            find(By.cssSelector("button[data-edit-text='Commit new file']")).click();
        }
        catch (Exception e)
        {
            find(By.cssSelector("a.btn.d-none.d-md-inline-block")).click();
        }
        return Pair.of(find(By.className("file-wrap")).getText(), file);
    }

    public void login()
    {
        loginBtn.click();
    }

    public String getStringPage() {
        return find(By.tagName("body")).getText();
    }
}
