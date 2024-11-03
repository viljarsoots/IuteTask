import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TestTask {

    @Test
    public void printFiveBirthdays(){

        open("https://www.google.com/");
        $(By.id("L2AGLb")).click();
        String googleUrl = WebDriverRunner.url();
        Assert.assertEquals(googleUrl, "https://www.google.com/");

        String googleTitle = title();
        Assert.assertEquals(googleTitle, "Google");

        $(By.name("q")).setValue("imdb").pressEnter();

        $("#res").shouldHave(Condition.text("imdb"));
        $(By.xpath("//h3[text()='IMDb: Ratings, Reviews, and Where to Watch the Best Movies & TV ...']")).shouldBe(visible).click();

        String imdbUrl = WebDriverRunner.url();
        Assert.assertEquals(imdbUrl, "https://www.imdb.com/");

        String imdbTitle = title();
        Assert.assertEquals(imdbTitle, "IMDb: Ratings, Reviews, and Where to Watch the Best Movies & TV Shows");
        $(By.xpath("//button[text()='Decline']")).should(exist).click();

        SelenideElement menuButton = $(By.className("ipc-responsive-button__text"));
        menuButton.should(Condition.visible);
        menuButton.shouldHave(text("menu"));
        menuButton.click();

        $(By.xpath("//span[text()='Born Today']")).shouldBe(visible).click();
        ElementsCollection linkedList = $$(By.tagName("h3"));
        linkedList.shouldHave(sizeGreaterThan(5));

        for(int i = 0; i< 5; i++){
            System.out.println(linkedList.get(i).getText().substring(2));
        }
    }


}
