package com.eking.spmanager.Utils;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-21
 * @Description
 **/

public interface Utils {
    //public String showIsActived;
    UtilsImpl.Combox combineList(Object a, Object b);
    List<UtilsImpl.idx> findAllGPRole();
    Timestamp getCurrentTime();
}
