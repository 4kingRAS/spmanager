package com.eking.spmanager.Utils;

import com.eking.spmanager.entity.Goods;
import org.springframework.data.domain.Page;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-21
 * @Description
 **/

public interface Utils {
    //public String showIsActived;
    <T> Page<T> convertPage(int page, int size, List<T> clist);
    UtilsImpl.Combox combineList(Object a, Object b);
    List<UtilsImpl.idx> findAllGPRole();
    Timestamp getCurrentTime();
}
