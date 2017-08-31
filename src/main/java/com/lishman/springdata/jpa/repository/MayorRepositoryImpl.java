package com.lishman.springdata.jpa.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

/* 
 * Spring will find this class because the name follows a convention of 
 * 
 *      <InterfaceName>Impl
 * 
 * An alternative would be to specify 
 * 
 *      @Repository("mayorRepositoryImpl")
 *      
 * then the class could be called anything.
 *  
 * However, in this case it would need to be picked up by a component scan.
 * 
 *      @ComponentScan(basePackageClasses=RepositoryPackage.class)
 */
public class MayorRepositoryImpl implements MayorRepositoryCustom {
    
    @Autowired private JdbcOperations operations;

    public int getNameLength(int mayorId) {
        String name = operations.queryForObject(
                "SELECT mayor_name FROM mayor WHERE mayor_id = ?", 
                String.class,
                mayorId);
        return name.length();
    }

}