package tsai.spring.boot.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tsai.spring.boot.util.OShiUtil;

import java.util.List;
import java.util.Map;

/**
 * OShi 硬件信息获取接口
 *
 * @author caixibei
 */
@Slf4j
@RestController
@RequestMapping(value = "/oshi")
public class OShiController {

    /**
     * 获取 JVM 信息
     *
     * @return {@link Map}
     */
    @GetMapping(value = "/getJVMInfo")
    public Map<String, Object> getJVMInfo() {
        Map<String, Object> jvmInfo = OShiUtil.getJvmInfo();
        log.info("===========> jvm information::{}", jvmInfo);
        return jvmInfo;
    }

    /**
     * 获取系统进程信息
     *
     * @return {@link Map}
     */
    @GetMapping(value = "/getProcessesInfo")
    public Map<String, Object> getProcessesInfo() {
        Map<String, Object> processInfo = OShiUtil.getProcessesInfo();
        log.info("===========> process information::{}", processInfo);
        return processInfo;
    }

    /**
     * 获取系统信息
     *
     * @return {@link Map}
     */
    @GetMapping(value = "/getSystemInfo")
    public Map<String, Object> getSystemInfo() {
        Map<String, Object> systemInfo = OShiUtil.getSystemInfo();
        log.info("===========> process information::{}", systemInfo);
        return systemInfo;
    }

    /**
     * 获取电源信息，虚拟机宿主机不支持电源信息
     *
     * @return @return {@link List}
     */
    @GetMapping(value = "/getPowerSourceInfo")
    public List<Map<String, Object>> getPowerSourceInfo() {
        List<Map<String, Object>> powerSourceInfo = OShiUtil.getPowerSourceInfo();
        log.info("===========> power information::{}", powerSourceInfo);
        return powerSourceInfo;
    }

    /**
     * 获取声卡信息
     *
     * @return @return {@link List}
     */
    @GetMapping(value = "/getSoundCardInfo")
    public List<Map<String, Object>> getSoundCardInfo() {
        List<Map<String, Object>> soundCardInfo = OShiUtil.getSoundCardInfo();
        log.info("===========> sound card information::{}", soundCardInfo);
        return soundCardInfo;
    }

    /**
     * 获取显卡信息
     *
     * @return @return {@link List}
     */
    @GetMapping(value = "/getGraphicsCardsInfo")
    public List<Map<String, Object>> getGraphicsCardsInfo() {
        List<Map<String, Object>> graphicsCardsInfo = OShiUtil.getGraphicsCardsInfo();
        log.info("===========> graphics card information::{}", graphicsCardsInfo);
        return graphicsCardsInfo;
    }

    /**
     * 获取USB设备信息
     *
     * @return @return {@link List}
     */
    @GetMapping(value = "/getUsbDevicesInfo")
    public List<Map<String, Object>> getUsbDevicesInfo() {
        List<Map<String, Object>> usbDevicesInfo = OShiUtil.getUsbDevicesInfo();
        log.info("===========> usb device information::{}", usbDevicesInfo);
        return usbDevicesInfo;
    }

    /**
     * 获取网络接口信息
     *
     * @return @return {@link List}
     */
    @GetMapping(value = "/getNetWorkInterfaces")
    public List<Map<String, Object>> getNetWorkInterfaces() {
        List<Map<String, Object>> netWorkInterfaces = OShiUtil.getNetWorkInterfaces();
        log.info("===========> network interface information::{}", netWorkInterfaces);
        return netWorkInterfaces;
    }

    /**
     * 获取逻辑卷组信息
     *
     * @return @return {@link List}
     */
    @GetMapping(value = "/getLogicalVolumeGroupInfo")
    public List<Map<String, Object>> getLogicalVolumeGroupInfo() {
        List<Map<String, Object>> logicalVolumeGroupInfo = OShiUtil.getLogicalVolumeGroupInfo();
        log.info("===========> logical volume group information::{}", logicalVolumeGroupInfo);
        return logicalVolumeGroupInfo;
    }

    /**
     * 获取磁盘存储信息
     *
     * @return @return {@link List}
     */
    @GetMapping(value = "/getDiskStoreInfo")
    public List<Map<String, Object>> getDiskStoreInfo() {
        List<Map<String, Object>> diskStoreInfo = OShiUtil.getDiskStoreInfo();
        log.info("===========> disk store group information::{}", diskStoreInfo);
        return diskStoreInfo;
    }

    /**
     * 获取网络参数信息
     *
     * @return @return {@link List}
     */
    @GetMapping(value = "/getNetworkParamsInfo")
    public Map<String, Object> getNetworkParamsInfo() {
        Map<String, Object> networkParamsInfo = OShiUtil.getNetworkParamsInfo();
        log.info("===========> network params information::{}", networkParamsInfo);
        return networkParamsInfo;
    }

    /**
     * 获取IP统计信息
     *
     * @return @return {@link List}
     */
    @GetMapping(value = "/getIpStatistics")
    public Map<String, Object> getIpStatistics() {
        Map<String, Object> ipStatistics = OShiUtil.getIpStatistics();
        log.info("===========> ip statistic information::{}", ipStatistics);
        return ipStatistics;
    }

    /**
     * 获取文件系统信息
     *
     * @return @return {@link List}
     */
    @GetMapping(value = "/getFileSystemInfo")
    public Map<String, Object> getFileSystemInfo() {
        Map<String, Object> fileSystemInfo = OShiUtil.getFileSystemInfo();
        log.info("===========> file system information::{}", fileSystemInfo);
        return fileSystemInfo;
    }

    /**
     * 获取内存信息
     *
     * @return @return {@link List}
     */
    @GetMapping(value = "/getMemoryInfo")
    public Map<String, Object> getMemoryInfo() {
        Map<String, Object> memoryInfo = OShiUtil.getMemoryInfo();
        log.info("===========> memory information::{}", memoryInfo);
        return memoryInfo;
    }
}
