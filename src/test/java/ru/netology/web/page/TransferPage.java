package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import net.bytebuddy.pool.TypePool;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TransferPage {

    private final SelenideElement amount = $("[data-test-id=amount] input");
    private final SelenideElement cardFrom = $("[data-test-id=from] input");
    private final SelenideElement cardTo = $("[data-test-id=to] input");
    private final SelenideElement depositButton = $("[data-test-id=action-transfer]");
    private final SelenideElement messageError = $("[data-test-id=error-notification]");
    private final SelenideElement cancelButton = $("[data-test-id=action-cancel]");

    public TransferPage() {
        $x("//*[contains(text(), 'Пополнение карты')]").shouldBe(Condition.visible);
    }

    public DashboardPage deposit(int depositAmount, String sourceCard) {
        setAmount(depositAmount);
        setSourceCard(sourceCard);
        depositButton.click();
        return new DashboardPage();
    }

    public void setSourceCard(String sourceCard) {
        cardFrom.sendKeys(Keys.CONTROL + "A");
        cardFrom.sendKeys(Keys.DELETE);
        cardFrom.setValue(sourceCard);
    }

    public void setAmount(int depositAmount) {
        amount.sendKeys(Keys.CONTROL + "A");
        amount.sendKeys(Keys.DELETE);
        amount.setValue(Integer.toString(depositAmount));
    }


    public void setAmount(String depositAmount) {
        amount.sendKeys(Keys.CONTROL + "A");
        amount.sendKeys(Keys.DELETE);
        amount.setValue(depositAmount);
    }

    public void checkErrorVisible() {
        messageError.shouldBe(Condition.visible);
    }


    public void checkEmptyInput() {
        amount.shouldBe(Condition.empty);
    }

    public void clickCancel() {
        cancelButton.click();
    }
}