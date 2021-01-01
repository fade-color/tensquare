import com.tensquare.encrypt.EncryptApplication;
import com.tensquare.encrypt.rsa.RsaKeys;
import com.tensquare.encrypt.service.RsaService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EncryptApplication.class)
public class EncryptTest {

    @Autowired
    private RsaService rsaService;

    @Before
    public void before() throws Exception{
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void genEncryptDataByPubKey() {
        String data = "{\"title\":\"java\"}";

        try {

            String encData = rsaService.RSAEncryptDataPEM(data, RsaKeys.getServerPubKey());

            System.out.println("data: " + data);
            System.out.println("encData: " + encData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() throws Exception {
        String requestData = "QzBPpSzKTv02J15sNZmfqIHcRYEby+Gy7k79q6e1kcVRpA4ycozUwjOFW9nTer+Qq601Gmqq8XliOlyFlvfD19/QViOGk+YPEfRWzoDjb86HOqFQMYj1suegoMkEeyGuAC++eUcvw/Sj1Be38r5IXIudIxImIDESRlV9HgKumb2LoqcT/OAv8rE35vuY4/jSIsP9Ls1WJCKidpQOC5Jw92Wfz8D71Seo36MwtcqyRHbrtDCXFdSID7nVfCF0P3JN2GZOng/MpyZ+EAe3G8b4dDkv7Y2/NuGV9vZZ2Ceq66v10EcgBReTYYfhmI7Y1B20QJijlVnDCD1XsfkXvu2b9g==";

        String s = rsaService.RSADecryptDataPEM(requestData, RsaKeys.getServerPrvKeyPkcs8());
        System.out.println(s);
    }
}
