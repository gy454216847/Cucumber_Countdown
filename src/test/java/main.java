import core.CommonWeb;

import java.io.IOException;

public class main
{
    public static void main(String[] args) throws IOException {
        CommonWeb.openBrowser("chrome", "https://www.countdown.co.nz/");
        CommonWeb.quit();
        CommonWeb.log.info("test");

//        System.out.println(CommonWeb.cuurentTime());
    }
}
