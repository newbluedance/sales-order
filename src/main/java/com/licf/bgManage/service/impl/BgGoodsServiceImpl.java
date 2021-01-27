package com.licf.bgManage.service.impl;

import ch.qos.logback.core.util.FileUtil;
import com.common.base.BaseServiceImpl;
import com.licf.bgManage.entity.BgGoods;
import com.licf.bgManage.entity.dto.BgGoodsParam;
import com.licf.bgManage.entity.dto.BgGoodsResult;
import com.licf.bgManage.service.BgGoodsService;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author lichunfeng
 * @date 2020-08-20 20:02:46
 */
@Service
public class BgGoodsServiceImpl extends BaseServiceImpl<BgGoods, BgGoodsParam, BgGoodsResult> implements BgGoodsService {

    @Override
    public void save(MultipartFile file, BgGoodsParam bgGoodsParam) throws IOException {

        BgGoods bgGoods = converter.paramToEntity(bgGoodsParam);

        if (bgGoods.getId() == null) {
            mapper.insertSelective(bgGoods);
        }
//        String bootPath = ResourceUtils.getFile("classpath:").getAbsolutePath().concat(File.separator);
        String bootPath = new ApplicationHome(getClass()).getSource().getParent().concat(File.separator);
        String subPath = "images" + File.separator + "goodMainImgs".concat(File.separator);
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String targetFileName = String.valueOf(bgGoods.getId()).concat(extension);
        File targetFile = new File(bootPath.concat(subPath).concat(targetFileName));
        FileUtil.createMissingParentDirectories(targetFile);
        //把文件保存到硬盘
        FileCopyUtils.copy(file.getBytes(), targetFile);

        bgGoods.setMainImg(subPath.concat(targetFileName));

        mapper.updateByPrimaryKeySelective(bgGoods);

    }
}