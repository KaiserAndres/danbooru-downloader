import com.kai.urlGetter.Configuration;
import com.kai.urlGetter.Url;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UrlTests{

    private Url url;
    private Configuration cfg;
    @Before
    public void setUp() {
        cfg = new Configuration();
        url = new Url("https://hijiribe.donmai.us", cfg);
    }

    @Test
    public void baseUrlIsValid() {
        Assert.assertEquals(url.toString(), "https://hijiribe.donmai.us");
    }

    @Test
    public void singleTagUrlIsValid() {
        String tag = "inubashiri_momiji";
        cfg.addDesiredCategory(tag);
        Assert.assertEquals(url.toString(), "https://hijiribe.donmai.us/posts?tags=inubashiri_momiji");
    }


}
