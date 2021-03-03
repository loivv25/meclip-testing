package com.dts.qlnhanvien.repository;

import com.dts.qlnhanvien.document.LSDiemDanh;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LSDiemDanhRepo extends MongoRepository<LSDiemDanh, String> {
}
