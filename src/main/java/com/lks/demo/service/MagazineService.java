/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lks.demo.service;

import com.lks.demo.model.Magazine;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Neeraj
 */
public interface MagazineService {
    public Magazine addNewMagazine(Magazine magazine);
    public Magazine updateMagazine(Magazine magazine);
    public void deleteMagazine(int magazineId);
    public List<Magazine> getAllMagazines();
    public Magazine getMagazineById(int magazineId);
   
}
