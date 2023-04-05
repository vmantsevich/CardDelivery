import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
public class DeliveryCardTest {
    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

        @Test
        void shouldCheckApplication() {
            open("http://localhost:9999");
            $("[data-test-id='city'] input").setValue("Самара");
            String currentDate = generateDate(4, "dd.MM.yyyy");
            $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
            $("[data-test-id='date'] input").sendKeys(currentDate);
            $("[data-test-id='name'] input").setValue("Алла-Виктория");
            $("[data-tes-id='phone' input").setValue("+79267098757");
            $("[data-test-id='agreement']").click();
            $(".notification__content").shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentDate));
        }

    }



