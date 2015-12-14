package com.mll.provider.plugin.repository;

import com.mll.provider.plugin.entity.FileInfo;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * Created by rocky on 2015/12/14.
 */
public interface FileInfoRepository extends CrudRepository<FileInfo, String> {


}
