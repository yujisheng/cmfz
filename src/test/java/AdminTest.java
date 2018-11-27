import com.baizhi.cmfz.CmfzApplication;
import com.baizhi.cmfz.dao.AdminDao;
import com.baizhi.cmfz.entity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CmfzApplication.class)
public class AdminTest {

    @Autowired
    private AdminDao adminDao;

    @Test
    public void m1() {
        Admin admin = adminDao.adminLogin("admin", "123456");
        System.out.println("admin = " + admin);
    }
}
