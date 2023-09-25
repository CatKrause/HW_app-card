package ru.netology.service;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.open;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class appCardTest {
    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldFillOutTheFormCorrectly() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        String planningDate = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Высилий Иванович Пупкин");
        $("[data-test-id='phone'] input").setValue("+79212345678");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(withText("Мы гарантируем безопасность")).shouldBe(visible);
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(30));
        $("[data-test-id='notification']").shouldHave(text("Встреча успешно забронирована на " + planningDate));

    }

}
