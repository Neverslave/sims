package com.util;

import com.mybatis.auto.auto.Menu;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class simsBizUtil {

    private static final Logger log = LoggerFactory
            .getLogger(simsBizUtil.class);

    /**
     * 菜单列表转化为菜单树
     *
     * @param list
     * @return
     */
    public static List<Menu> menuListToTree(List<Map<String, Object>> list) {
        List<Menu> menuList = new ArrayList<>();
        if (list == null || list.isEmpty()) {
            log.warn("menu list is empty!");
            return menuList;
        }

        Map<String, List<Menu>> childMenus = new LinkedHashMap<>();

        for (Map<String, Object> map : list) {
            Menu menu = new Menu();
            BeanUtil.mapToBean(map, menu);
            if (StringUtils.isBlank(menu.getParentId())) {
                menuList.add(menu);
            } else {
                if (childMenus.containsKey(menu.getParentId())) {
                    childMenus.get(menu.getParentId()).add(menu);
                } else {
                    List<Menu> childMenuList = new ArrayList<>();
                    childMenuList.add(menu);
                    childMenus.put(menu.getParentId(), childMenuList);
                }

            }
        }

        // 如果没有顶级菜单，则返回空
        if (menuList == null || menuList.isEmpty()) {
            return menuList;
        }

        int nodeId = 0; // 初始化为0
        appendChildMenu(menuList, childMenus, nodeId);

        return menuList;
    }

    /**
     * 递归得到菜单树
     *
     * @param menuList
     * @param childMenus
     */
    private static int appendChildMenu(List<Menu> menuList,
                                       Map<String, List<Menu>> childMenus, int nodeId) {
        for (Menu menu : menuList) {
            menu.setNodeId(nodeId++);
            if (childMenus.containsKey(menu.getMenuId())) {
                List<Menu> subMenuList = childMenus.remove(menu.getMenuId());
                menu.setChildFlag(true);
                    menu.setSubMenuList(subMenuList);
                nodeId = appendChildMenu(subMenuList, childMenus, nodeId);
            }
        }
        return nodeId;
    }
}
