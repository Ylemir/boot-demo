package me.boot.base;

import com.google.common.collect.ImmutableMap;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import me.boot.base.dto.FiledDifference;
import me.boot.base.dto.MultiResult;
import me.boot.base.dto.PageResult;
import me.boot.base.util.CommonUtils;
import me.boot.base.util.FileUtil;
import me.boot.base.util.ObjectDiffUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

/**
 * GuavaTest
 *
 * @since 2023/06/14
 */
public class GuavaTest {

    @Test
    public void testPrimitives() {
        ImmutableMap<String, Integer> m1 = ImmutableMap.of("1", 1, "2", 2,
            "0", 3, "4", 4, "5", 5);
        ImmutableMap<String, Integer> m2 = ImmutableMap.of("1", 1, "2", 3,
            "3", 3, "4", 5, "5", 5);

        List<FiledDifference> diff = ObjectDiffUtils.diff(m1, m2);

        MultiResult<String> before = MultiResult.success(Arrays.asList("1", "1", "2"));
        PageResult<String> after = PageResult.success(Arrays.asList("1", "3", "2"), 5, 1, 1);

        System.err.println(ObjectDiffUtils.diff(before, after));
    }

    @Test
    public void diffList() {
        List<FiledDifference> list = Arrays.asList(new FiledDifference("1", 1, "2"),
            new FiledDifference("2", 1, "2"),
            new FiledDifference("3", 1, "2")
        );
        List<FiledDifference> list2 = Arrays.asList(new FiledDifference("1", 1, "2"),
            new FiledDifference("2", 2, "2"),
            new FiledDifference("4", 1, "2")
        );

        Pair<List<Object>, List<Object>> pair = ObjectDiffUtils.diffList(list, list2, "property");
        System.err.println(pair);

    }

    @Test
    public void test() {
        System.err.println(CommonUtils.isJavaBean(Status.ACTIVE.getClass()));
        System.err.println(CommonUtils.isJavaBean(Stream.class));
        System.err.println(CommonUtils.isJavaBean(Pair.class));
        System.err.println(CommonUtils.isJavaBean(File.class));
        System.err.println(CommonUtils.isJavaBean(FileUtil.class));
    }

    enum Status {
        ACTIVE,
        INACTIVE,
        DELETED
    }
}
