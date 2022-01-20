package com.bkap.factory;

import com.bkap.dao.CategoryDAOImp;
import com.bkap.dao.GeneralDao;
import com.bkap.dao.ProductDAOImp;

// Má mày lười vừa thôi code điiii

public class DAOFactory {
    public <T> GeneralDao getDaoType(GeneralDao<T> factoryClass) {
        if (factoryClass instanceof CategoryDAOImp) {
            return CategoryDAOImp.getInstance();
        } else if (factoryClass instanceof ProductDAOImp) {
            return ProductDAOImp.getInstance();
        }
        return null;
    }
}
