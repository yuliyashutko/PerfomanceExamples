package pages;

import org.openqa.selenium.By;
import static helpers.Locators.get;

public class HomePage {
        public final static String HOME_PAGE_URL = "http://www.issoft.by/";

        public final static By MENU_TECHNOLOGIES_ITEM = get("homePage.menuItemTechnologies");
        public final static By MENU_OUTSOURCING_ITEM = get("homePage.menuItemOutsourcing");
        public final static By MENU_OUR_SERVICES_ITEM = get("homePage.menuItemOurServices");
}
