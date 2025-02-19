package apiTests;

import com.testautomation.apitesting.utils.ExcelUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CountryTranslationTest {

    @DataProvider(name = "translationData")
    public Object[][] getTranslationData() throws IOException {
        String filePath = "/src/test/resources/testdata.xls";
        String sheetName = "Translations"; // Sheet name
        List<Map<String, String>> data = ExcelUtils.readExcel(filePath, sheetName);

        Object[][] testData = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            testData[i][0] = data.get(i).get("translation"); // Assuming "translation" is the column name
        }
        return testData;
    }

    @Test(dataProvider = "translationData")
    public void testCountryTranslation(String translation) {
        String baseUrl = "https://restcountries.com/v3.1/translation";
        String url = baseUrl + "/" + translation;
        System.out.println("url:" + url);

        Response response = RestAssured.given()
                .when()
                .get(url)
                .then()
                .extract()
                .response();

        System.out.println("status code:" + response.getStatusCode()+ " for "+translation);
        Assert.assertEquals(response.getStatusCode(), 200, "API call for " + translation);
    }
}