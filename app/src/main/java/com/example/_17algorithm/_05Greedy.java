package com.example._17algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

// 贪心算法
public class _05Greedy {
    public static void main(String[] args) {
        // 创建广播电台，放入到Map
        Map<String, HashSet<String>> broadcasts = new HashMap<>();
        // 将各个电台放入到 broadcasts
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        broadcasts.put("K1", hashSet1);

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        broadcasts.put("K2", hashSet2);

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        broadcasts.put("K3", hashSet3);

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        broadcasts.put("K4", hashSet4);

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        broadcasts.put("K5", hashSet5);

        // allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.addAll(hashSet1);
        allAreas.addAll(hashSet2);
        allAreas.addAll(hashSet3);
        allAreas.addAll(hashSet4);
        allAreas.addAll(hashSet5);

        // 创建一个ArrayList，存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        // 定义一个临时的集合，在遍历过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();
        // 定义一个maxKey，保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的key
        // 如果maxKey 不为空，则会加入到selects中
        String maxKey = null;
        while (allAreas.size() > 0) { // 如果allAreas 不为0，则表示还没有覆盖所有的地区
            // 每进行一次while，需要
            maxKey = null;

            // 遍历 broadcasts
            for (String key : broadcasts.keySet()) {
                // 没进行一次for
                tempSet.clear();
                // 当前这个key能够覆盖的地区
                HashSet<String> hashSet = broadcasts.get(key);
                tempSet.addAll(hashSet);
                // 求出tempSet 和 allAreas 集合的交集，交集会赋给tempSet
                tempSet.retainAll(allAreas);
                // 如果当前这个集合包含的未覆盖地区的数量，比maxKey执行的集合未覆盖的地区还多
                // tempSet.size() > broadcasts.get(maxKey).size() 体现贪心算法的特点，每次都选择最优的
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }

            // maxKey 不等于null，就应该将maxKey 加入selects
            if (maxKey != null) {
                selects.add(maxKey);
                // 将maxKey指向的广播电台覆盖的地区，从allAreas 去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println(selects);

    }
}
