/**
 * 
 */
package tools;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class Tools {

    public static String getShortMd5(String value) {
        return Hashing.md5().newHasher().putString(value, Charsets.UTF_8)
                .hash().toString().substring(16);

    }

    public static String getMd5(String value) {
        return Hashing.md5().newHasher().putString(value, Charsets.UTF_8)
                .hash().toString();

    }

}
