import com.kai.urlGetter.Configuration;
import com.kai.urlGetter.Url;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UrlTests{

    private Url url;
    private Configuration cfg;
    private String tag1;
    private String tag2;
    private int pageNumber;

    @Before
    public void setUp() {
        cfg = new Configuration();
        url = new Url("https://hijiribe.donmai.us", cfg);
        tag1 = "inubashiri_momiji";
        tag2 = "shameimaru_aya";
        pageNumber = 14;
    }

    @Test
    public void defaultReachesMainSite() {
        url = new Url("https://hijiribe.donmai.us");
        Assert.assertEquals(url.toString(), "https://hijiribe.donmai.us");
    }

    @Test
    public void customConfigWorksProperly() {
        Configuration myCfg = new Configuration();
        myCfg.addDesiredCategory("funny");
        myCfg.addDesiredCategory("special");
        url.setConfig(myCfg);
        Assert.assertEquals(url.toString(), "https://hijiribe.donmai.us/posts?tags=funny+special");
    }

    @Test
    public void baseUrlIsValid() {
        Assert.assertEquals(url.toString(), "https://hijiribe.donmai.us");
    }

    @Test
    public void singleTagUrlIsValid() {
        cfg.addDesiredCategory(tag1);
        Assert.assertEquals(url.toString(), "https://hijiribe.donmai.us/posts?tags=inubashiri_momiji");
    }

    @Test
    public void urlCanUsePageNumbers() {
        cfg.addDesiredCategory(tag1);
        url.setPageNumber(pageNumber);
        Assert.assertEquals(url.toString(), "https://hijiribe.donmai.us/posts?page=14&tags=inubashiri_momiji");
    }

    @Test
    public void urlCanHaveMultipleCategories() {
        cfg.addDesiredCategory(tag2);
        cfg.addDesiredCategory(tag1);
        Assert.assertEquals(url.toString(), "https://hijiribe.donmai.us/posts?tags=shameimaru_aya+inubashiri_momiji");
    }

    @Test
    public void urlCanHaveMultipleCategoriesAndAdvancedPageNumber() {
        url.setPageNumber(2);
        cfg.addDesiredCategory(tag2);
        cfg.addDesiredCategory(tag1);
        Assert.assertEquals(url.toString(), "https://hijiribe.donmai.us/posts?page=2&tags=shameimaru_aya+inubashiri_momiji");
    }

}
