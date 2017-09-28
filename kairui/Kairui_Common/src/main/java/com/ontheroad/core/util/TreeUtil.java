package com.ontheroad.core.util;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 先定义 M 泛型并且继承Tree,之后M在后续的方法快中共用。
 * 给方法定义了一个List<M> 的返回值
 */

public class TreeUtil {
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <M extends Tree> List<M> getResult(List<M> src) {
        List<M> parents = new ArrayList<M>();
        for (M ent : src) {
            if (ent.isRoot()) {
                M result = ent;
                result.setChildren(new ArrayList<M>());
                parents.add(result);
            }
        }
        List<M> last = new ArrayList<M>();
        for (M ent : src) {
            if (!ent.isRoot()) {
                last.add(ent);
            }
        }
        buildTree(parents, last);
        return parents;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static <M extends Tree> void buildTree(List<M> parents, List<M> others) {
        List<M> record = new ArrayList<M>();
        for (Iterator<M> it = parents.iterator(); it.hasNext(); ) {
            M vi = it.next();
            if (vi.getId() != null) {
                for (Iterator<M> otherIt = others.iterator(); otherIt.hasNext(); ) {
                    M inVi = otherIt.next();
                    if (vi.getId().equals(inVi.getPid())) {
                        if (null == vi.getChildren()) {
                            vi.setChildren(new ArrayList<M>());
                        }
                        vi.getChildren().add(inVi);
                        record.add(inVi);
                        otherIt.remove();
                    }
                }
            }
        }
        if (others.size() == 0) {
            return;
        } else {
            buildTree(record, others);
        }
    }
}