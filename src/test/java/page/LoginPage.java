package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id='login'] input");
    private SelenideElement passwordField = $("[data-test-id='password'] input");
    private SelenideElement loginButton = $("[data-test-id='action-login']");
    private SelenideElement errorMessage = $("[data-test-id='error-notification']");

    public VerificationPage validLogin(DataHelper.AuthInfo authInfo) {
        loginField.setValue(authInfo.getLogin());
        passwordField.setValue(authInfo.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void cleanForm() {
        loginField.doubleClick().sendKeys(Keys.BACK_SPACE);
        passwordField.doubleClick().sendKeys(Keys.BACK_SPACE);
    }

    public void getErrorMessage() {
        errorMessage.shouldHave(Condition.text("Ошибка! " + "Неверно указан логин или пароль"));
        errorMessage.shouldBe(Condition.visible);
    }

    public void getUserBlock() {
        errorMessage.shouldHave(Condition.text("Ошибка! " + "Пользователь заблокирован"));
        errorMessage.shouldBe(Condition.visible);
    }
}
