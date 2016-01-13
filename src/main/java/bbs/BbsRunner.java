package bbs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import tools.DateTools;
import tools.HttpClientUtil;
import tools.RandomString;

public class BbsRunner {
    private static final String COOKIE_STRING = "SMcs_2132_saltkey=vn02334T; SMcs_2132_lastvisit=1451889682; SMcs_2132_nofavfid=1; SMcs_2132_st_p=0%7C1452245992%7C53802b938d71485410e66e140b4bd5c2; SMcs_2132_viewid=tid_127; SMcs_2132_seccode=1.71c3567a68eb6f86f3; SMcs_2132_ulastactivity=e2d7wig1fxDIgni4501Ei0m9CNYSQk%2Bh60kH129U8oiCNKbzjLf9; SMcs_2132_auth=138dlECNboEtJJVXntWcRD%2FluMUvtPZ%2FSWLaLzZ%2BCANNFZaiA7zQnrnBh2IV7Dfmk%2BjWTSZTTyr1I0whClmc; SMcs_2132_creditbase=0D0D242D0D0D0D0D0D0; SMcs_2132_lastcheckfeed=1%7C1452246345; SMcs_2132_security_cookiereport=1674YLwSzwgoQ9nIXo9t6%2BQiEL1HfC42P54fQUg2ROtrHnLFs5%2Bf; SMcs_2132_visitedfid=41D2; SMcs_2132_forum_lastvisit=D_2_1452246302D_41_1452246355; SMcs_2132_st_t=1%7C1452246438%7C34ca0dd01ca1b30f5acc5f807cd97598; SMcs_2132_sid=qws60d; SMcs_2132_editormode_e=1; SMcs_2132_smile=1D1; CNZZDATA1257065146=341156930-1451893284-%7C1452245972; SMcs_2132_noticeTitle=1; SMcs_2132_lastact=1452246613%09forum.php%09ajax";

    public static void main(String[] args) {
        DateTools dateTools = new DateTools();
        for (int i = 0; i < 100; i++) {
            dateTools.increaseDay();
            Map<String, String> map = new HashMap<String, String>();
            map.put("formhash", "5dd885a9");
            map.put("posttime", "1452246438");
            map.put("wysiwyg", "1");
            map.put("subject", dateTools.getSimpleDataString()
                    + "搜狐视频VIP会员账号分享");
            map.put("message",
                    dateTools.getSimpleDataString()
                            + " 搜狐视频VIP会员账号分享\n[hide] ，账号："
                            + RandomString.getMailAddress() + "， 密码："
                            + RandomString.getRandomString(10) + " [/hide]");
            map.put("replycredit_extcredits", "0");
            map.put("replycredit_times", "1");
            map.put("replycredit_membertimes", "1");
            map.put("replycredit_random", "100");
            map.put("tags", "搜狐,会员,账号");
            map.put("allownoticeauthor", "1");
            map.put("usesig", "1");

            try {
                System.out
                        .println(HttpClientUtil
                                .getPostContent(
                                        "http://vipfxbbs.com/forum.php?mod=post&action=newthread&fid=39&extra=&topicsubmit=yes",
                                        map, COOKIE_STRING));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
