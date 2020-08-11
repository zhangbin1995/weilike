import lombok.Data;
import redis.clients.jedis.Jedis;

public class Demo {

    private Jedis jedis = new Jedis("localhost", 6379);

    public void like(int uid, int mid) {
        String suid = String.valueOf(uid);
        String smid = String.valueOf(mid);
        // 若已有该记录，说明以点赞过
        if (jedis.sismember(suid, smid)) {
            System.out.println("已点赞过～");
        } else {
            // 未点赞过，插入set中，表示点赞
            jedis.sadd(String.valueOf(uid), String.valueOf(mid));
        }
    }

    public boolean isLiked(int uid, int mid) {
        String suid = String.valueOf(uid);
        String smid = String.valueOf(mid);
        // 存在该记录，返回true
        if (jedis.sismember(suid, smid)) {
            System.out.println("已点赞过～");
            return true;
        }
        // 不存在该记录，返回false
        return false;
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        System.out.println(demo.jedis.ping());
        demo.like(1, 7);
        System.out.println(demo.isLiked(1, 7));
    }

}
