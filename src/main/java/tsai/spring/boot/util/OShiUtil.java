package tsai.spring.boot.util;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.*;
import oshi.util.EdidUtil;
import oshi.util.FormatUtil;
import oshi.util.ParseUtil;

import java.lang.management.ManagementFactory;
import java.net.NetworkInterface;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Oshi 硬件信息读取工具类
 *
 * @author caixibei
 */
@SuppressWarnings({"unused"})
public class OShiUtil {

    private static final SystemInfo systemInfo = new SystemInfo();

    private static final OperatingSystem operatingSystem = systemInfo.getOperatingSystem();

    private static final HardwareAbstractionLayer hal = systemInfo.getHardware();

    /**
     * 读取显示器信息
     *
     * @return {@link List}
     * @deprecated 详见 {@code getDisplayInformation()} 方法
     */
    @Deprecated
    public static List<Map<String, Object>> getDisplayInfo() {
        final List<Display> displays = hal.getDisplays();
        final List<Map<String, Object>> displayInfos = new ArrayList<>(displays.size());
        for (int i = 0; i < displays.size(); i++) {
            Map<String, Object> displayInfo = new ConcurrentHashMap<>();
            displayInfo.put("index", String.valueOf(i));
            final Display display = displays.get(i);
            displayInfo.put("instance", display);
            displayInfo.put("toString", String.valueOf(display));
            displayInfos.add(displayInfo);
        }
        return displayInfos;
    }

    /**
     * 读取声卡信息
     *
     * @return {@link List}
     */
    public static List<Map<String, Object>> getSoundCardInfo() {
        final List<SoundCard> soundCards = hal.getSoundCards();
        final List<Map<String, Object>> soundCardInfos = new ArrayList<>(soundCards.size());
        for (int i = 0; i < soundCards.size(); i++) {
            Map<String, Object> soundCard = new ConcurrentHashMap<>();
            soundCard.put("index", String.valueOf(i));
            final SoundCard soundCardInst = soundCards.get(i);
            soundCard.put("toString", String.valueOf(soundCardInst));
            soundCard.put("codec", soundCardInst.getCodec());
            soundCard.put("name", soundCardInst.getName());
            soundCard.put("driverVersion", soundCardInst.getDriverVersion());
            soundCardInfos.add(soundCard);
        }
        return soundCardInfos;
    }

    /**
     * 读取显卡信息
     *
     * @return {@link List}
     */
    public static List<Map<String, Object>> getGraphicsCardsInfo() {
        final List<GraphicsCard> graphicsCards = hal.getGraphicsCards();
        final List<Map<String, Object>> graphicsCardsInfos = new ArrayList<>(graphicsCards.size());
        for (int i = 0; i < graphicsCards.size(); i++) {
            Map<String, Object> gcInfo = new ConcurrentHashMap<>();
            gcInfo.put("index", String.valueOf(i));
            GraphicsCard graphicsCard = graphicsCards.get(i);
            gcInfo.put("toString", String.valueOf(graphicsCard));
            gcInfo.put("name", graphicsCard.getName());
            gcInfo.put("deviceId", graphicsCard.getDeviceId());
            gcInfo.put("versionInfo", graphicsCard.getVersionInfo());
            gcInfo.put("vendor", graphicsCard.getVendor());
            gcInfo.put("vRam", graphicsCard.getVRam());
            gcInfo.put("vRamFormat", FormatUtil.formatBytes(graphicsCard.getVRam()));
            graphicsCardsInfos.add(gcInfo);
        }
        return graphicsCardsInfos;
    }

    /**
     * USB 设备信息
     *
     * @return {@link List}
     */
    public static List<Map<String, Object>> getUsbDevicesInfo() {
        final List<UsbDevice> usbDevices = hal.getUsbDevices(true);
        final List<Map<String, Object>> usbDevicesInfos = new ArrayList<>(usbDevices.size());
        for (int i = 0; i < usbDevices.size(); i++) {
            Map<String, Object> usbDeviceInfo = new ConcurrentHashMap<>();
            usbDeviceInfo.put("index", String.valueOf(i));
            final UsbDevice usbDevice = usbDevices.get(i);
            usbDeviceInfo.put("toString", String.valueOf(usbDevice));
            usbDeviceInfo.put("name", usbDevice.getName());
            usbDeviceInfo.put("uniqueDeviceId", usbDevice.getUniqueDeviceId());
            usbDeviceInfo.put("vendor", usbDevice.getVendor());
            usbDeviceInfo.put("productId", usbDevice.getProductId());
            usbDeviceInfo.put("serialNumber", usbDevice.getSerialNumber());
            usbDeviceInfo.put("vendorId", usbDevice.getVendorId());
            final List<UsbDevice> connectedDevices = usbDevice.getConnectedDevices();
            usbDeviceInfo.put("connectedDevices", connectedDevices);
            usbDevicesInfos.add(usbDeviceInfo);
        }
        return usbDevicesInfos;
    }

    /**
     * 网络参数信息
     *
     * @return {@link Map}
     */
    public static Map<String, Object> getNetworkParamsInfo() {
        Map<String, Object> networkParamsInfo = new ConcurrentHashMap<>();
        NetworkParams networkParams = operatingSystem.getNetworkParams();
        networkParamsInfo.put("toString", networkParams);
        networkParamsInfo.put("hostName", networkParams.getHostName());
        networkParamsInfo.put("dnsServers", Arrays.asList(networkParams.getDnsServers()));
        networkParamsInfo.put("domainName", networkParams.getDomainName());
        networkParamsInfo.put("ipv4Gateway", networkParams.getIpv4DefaultGateway());
        networkParamsInfo.put("ipv6Gateway", networkParams.getIpv6DefaultGateway());
        return networkParamsInfo;
    }

    /**
     * IP 信息统计
     *
     * @return {@link Map}
     */
    public static Map<String, Object> getIpStatistics() {
        final InternetProtocolStats internetProtocolStats = operatingSystem.getInternetProtocolStats();
        final Map<String, Object> ipStatisticsMap = new ConcurrentHashMap<>();
        // 统计 TCP v4 信息
        Map<String, Object> tcpV4 = new ConcurrentHashMap<>();
        final InternetProtocolStats.TcpStats tcPv4Stats = internetProtocolStats.getTCPv4Stats();
        tcpV4.put("toString", String.valueOf(tcPv4Stats));
        tcpV4.put("connectionFailures", tcPv4Stats.getConnectionFailures());
        tcpV4.put("connectionsActive", tcPv4Stats.getConnectionsActive());
        tcpV4.put("connectionsPassive", tcPv4Stats.getConnectionsPassive());
        tcpV4.put("connectionsReset", tcPv4Stats.getConnectionsReset());
        tcpV4.put("connectionsEstablished", tcPv4Stats.getConnectionsEstablished());
        tcpV4.put("inErrors", tcPv4Stats.getInErrors());
        tcpV4.put("outResets", tcPv4Stats.getOutResets());
        tcpV4.put("segmentsReceived", tcPv4Stats.getSegmentsReceived());
        tcpV4.put("segmentsRetransmitted", tcPv4Stats.getSegmentsRetransmitted());
        tcpV4.put("segmentsSent", tcPv4Stats.getSegmentsSent());
        ipStatisticsMap.put("tcpV4", tcpV4);
        // 统计 TCP v6 信息
        Map<String, Object> tcpV6 = new ConcurrentHashMap<>();
        final InternetProtocolStats.TcpStats tcPv6Stats = internetProtocolStats.getTCPv6Stats();
        tcpV6.put("toString", String.valueOf(tcPv6Stats));
        tcpV6.put("connectionFailures", tcPv6Stats.getConnectionFailures());
        tcpV6.put("connectionsActive", tcPv6Stats.getConnectionsActive());
        tcpV6.put("connectionsPassive", tcPv6Stats.getConnectionsPassive());
        tcpV6.put("connectionsReset", tcPv6Stats.getConnectionsReset());
        tcpV6.put("connectionsEstablished", tcPv6Stats.getConnectionsEstablished());
        tcpV6.put("inErrors", tcPv6Stats.getInErrors());
        tcpV6.put("outResets", tcPv6Stats.getOutResets());
        tcpV6.put("segmentsReceived", tcPv6Stats.getSegmentsReceived());
        tcpV6.put("segmentsRetransmitted", tcPv6Stats.getSegmentsRetransmitted());
        tcpV6.put("segmentsSent", tcPv6Stats.getSegmentsSent());
        ipStatisticsMap.put("tcpV6", tcpV6);
        // 统计 UDP v4 信息
        Map<String, Object> udpV4 = new ConcurrentHashMap<>();
        InternetProtocolStats.UdpStats udPv4Stats = internetProtocolStats.getUDPv4Stats();
        udpV4.put("toString", String.valueOf(udPv4Stats));
        udpV4.put("datagramsNoPort", udPv4Stats.getDatagramsNoPort());
        udpV4.put("datagramsReceived", udPv4Stats.getDatagramsReceived());
        udpV4.put("datagramsReceivedErrors", udPv4Stats.getDatagramsReceivedErrors());
        udpV4.put("datagramsSent", udPv4Stats.getDatagramsSent());
        ipStatisticsMap.put("udpV4", udpV4);
        // 统计 UDP v6 信息
        Map<String, Object> udpV6 = new ConcurrentHashMap<>();
        InternetProtocolStats.UdpStats udPv6Stats = internetProtocolStats.getUDPv6Stats();
        udpV6.put("toString", String.valueOf(udPv6Stats));
        udpV6.put("datagramsNoPort", udPv6Stats.getDatagramsNoPort());
        udpV6.put("datagramsReceived", udPv6Stats.getDatagramsReceived());
        udpV6.put("datagramsReceivedErrors", udPv6Stats.getDatagramsReceivedErrors());
        udpV6.put("datagramsSent", udPv6Stats.getDatagramsSent());
        ipStatisticsMap.put("udpV6", udpV6);
        // 网络连接信息
        final List<InternetProtocolStats.IPConnection> connections = internetProtocolStats.getConnections();
        final List<Map<String, Object>> ipConnectionInfoList = new ArrayList<>(connections.size());
        for (int i = 0; i < connections.size(); i++) {
            Map<String, Object> ipConnectionInfo = new ConcurrentHashMap<>();
            ipConnectionInfo.put("connectionIndex", i);
            final InternetProtocolStats.IPConnection ipConnection = connections.get(i);
            ipConnectionInfo.put("toString", String.valueOf(ipConnection));
            ipConnectionInfo.put("foreignAddress", Arrays.toString(ipConnection.getForeignAddress()));
            ipConnectionInfo.put("foreignPort", ipConnection.getForeignPort());
            ipConnectionInfo.put("localAddress", Arrays.toString(ipConnection.getLocalAddress()));
            ipConnectionInfo.put("state", ipConnection.getState());
            ipConnectionInfo.put("type", ipConnection.getType());
            ipConnectionInfo.put("localPort", ipConnection.getLocalPort());
            ipConnectionInfo.put("owningProcessId", ipConnection.getowningProcessId());
            ipConnectionInfo.put("receiveQueue", ipConnection.getReceiveQueue());
            ipConnectionInfo.put("transmitQueue", ipConnection.getTransmitQueue());
            ipConnectionInfoList.add(ipConnectionInfo);
        }
        ipStatisticsMap.put("ipConnections", ipConnectionInfoList);
        return ipStatisticsMap;
    }

    /**
     * 网络接口信息读取
     *
     * @return {@link List}
     */
    public static List<Map<String, Object>> getNetWorkInterfaces() {
        final List<NetworkIF> networkIFs = hal.getNetworkIFs();
        final List<Map<String, Object>> networkIFList = new ArrayList<>(networkIFs.size());
        for (int i = 0; i < networkIFs.size(); i++) {
            final Map<String, Object> networkIfMap = new ConcurrentHashMap<>();
            networkIfMap.put("index", i);
            final NetworkIF networkIF = networkIFs.get(i);
            networkIfMap.put("toString", String.valueOf(networkIF));
            networkIfMap.put("displayName", networkIF.getDisplayName());
            networkIfMap.put("name", networkIF.getName());
            networkIfMap.put("ifIndex", networkIF.getIndex());
            networkIfMap.put("iPv4addr", networkIF.getIPv4addr());
            networkIfMap.put("iPv6addr", networkIF.getIPv6addr());
            networkIfMap.put("macAddr", networkIF.getMacaddr());
            networkIfMap.put("speed", networkIF.getSpeed());
            networkIfMap.put("subnetMasks", networkIF.getSubnetMasks());
            networkIfMap.put("bytesRecv", networkIF.getBytesRecv());
            networkIfMap.put("bytesSent", networkIF.getBytesSent());
            networkIfMap.put("collisions", networkIF.getCollisions());
            networkIfMap.put("ifAlias", networkIF.getIfAlias());
            networkIfMap.put("ifOperStatus", networkIF.getIfOperStatus().name());
            networkIfMap.put("ifType", networkIF.getIfType());
            networkIfMap.put("inDrops", networkIF.getInDrops());
            networkIfMap.put("inErrors", networkIF.getInErrors());
            networkIfMap.put("mtu", networkIF.getMTU());
            networkIfMap.put("ndisPhysicalMediumType", networkIF.getNdisPhysicalMediumType());
            networkIfMap.put("outErrors", networkIF.getOutErrors());
            networkIfMap.put("packetsRecv", networkIF.getPacketsRecv());
            networkIfMap.put("packetsSent", networkIF.getPacketsSent());
            networkIfMap.put("prefixLengths", networkIF.getPrefixLengths());
            networkIfMap.put("timeStamp", networkIF.getTimeStamp());
            networkIfMap.put("isConnectorPresent", networkIF.isConnectorPresent());
            networkIfMap.put("isKnownVmMacAddr", networkIF.isKnownVmMacAddr());
            NetworkInterface networkInterface = networkIF.queryNetworkInterface();
            networkIfMap.put("networkInterface", String.valueOf(networkInterface));
            networkIFList.add(networkIfMap);
        }
        return networkIFList;
    }

    /**
     * 读取文件系统信息
     *
     * @return {@link List}
     */
    public static Map<String, Object> getFileSystemInfo() {
        final Map<String, Object> fsInfo = new ConcurrentHashMap<>();
        final FileSystem fileSystem = operatingSystem.getFileSystem();
        final List<Map<String, Object>> fileSystemInfos = new ArrayList<>();
        fsInfo.put("openFileDescriptors", fileSystem.getOpenFileDescriptors());
        fsInfo.put("maxFileDescriptors", fileSystem.getMaxFileDescriptors());
        fsInfo.put("fileDescriptors", String.format("%d/%d", fileSystem.getOpenFileDescriptors(), fileSystem.getMaxFileDescriptors()));
        fsInfo.put("fdUsageRate", (100d * fileSystem.getOpenFileDescriptors() / fileSystem.getMaxFileDescriptors()) + "%");
        final List<OSFileStore> fileStores = fileSystem.getFileStores();
        for (int i = 0; i < fileStores.size(); i++) {
            final Map<String, Object> fileStoreInfo = new ConcurrentHashMap<>();
            fileStoreInfo.put("index", i);
            final OSFileStore osFileStore = fileStores.get(i);
            fileStoreInfo.put("toString", String.valueOf(osFileStore));
            fileStoreInfo.put("name", osFileStore.getName());
            fileStoreInfo.put("description", osFileStore.getDescription());
            fileStoreInfo.put("totalSpace", FormatUtil.formatBytes(osFileStore.getTotalSpace()));
            fileStoreInfo.put("usedSpace", FormatUtil.formatBytes(osFileStore.getTotalSpace() - osFileStore.getUsableSpace()));
            fileStoreInfo.put("usableSpace", FormatUtil.formatBytes(osFileStore.getUsableSpace()));
            fileStoreInfo.put("usageRate", 100d * (osFileStore.getTotalSpace() - osFileStore.getUsableSpace()) / osFileStore.getTotalSpace());
            fileStoreInfo.put("volume", osFileStore.getVolume());
            fileStoreInfo.put("mount", osFileStore.getMount());
            fileStoreInfo.put("logicalVolume", osFileStore.getLogicalVolume());
            fileStoreInfo.put("totalInodes", FormatUtil.formatValue(osFileStore.getTotalInodes(), ""));
            fileStoreInfo.put("freeInodes", FormatUtil.formatValue(osFileStore.getFreeInodes(), ""));
            fileStoreInfo.put("inodesUsageRate", 100d * osFileStore.getFreeInodes() / osFileStore.getTotalInodes());
            fileSystemInfos.add(fileStoreInfo);
        }
        fsInfo.put("fileStores", fileSystemInfos);
        return fsInfo;
    }

    /**
     * 逻辑卷组信息
     *
     * @return {@link List}
     */
    public static List<Map<String, Object>> getLogicalVolumeGroupInfo() {
        final List<LogicalVolumeGroup> logicalVolumeGroups = hal.getLogicalVolumeGroups();
        final List<Map<String, Object>> lvgInfos = new ArrayList<>(logicalVolumeGroups.size());
        for (int i = 0; i < logicalVolumeGroups.size(); i++) {
            final LogicalVolumeGroup lvg = logicalVolumeGroups.get(i);
            final Map<String, Object> lvgInfo = new ConcurrentHashMap<>();
            lvgInfo.put("index", i);
            lvgInfo.put("toString", String.valueOf(lvg));
            lvgInfo.put("name", lvg.getName());
            lvgInfo.put("logicalVolumes", lvg.getLogicalVolumes());
            lvgInfo.put("physicalVolumes", lvg.getPhysicalVolumes());
            lvgInfos.add(lvgInfo);
        }
        return lvgInfos;
    }

    /**
     * 磁盘存储信息读取
     *
     * @return {@link List}
     */
    public static List<Map<String, Object>> getDiskStoreInfo() {
        List<HWDiskStore> diskStores = hal.getDiskStores();
        List<Map<String, Object>> dsList = new ArrayList<>(diskStores.size());
        for (int i = 0; i < diskStores.size(); i++) {
            HWDiskStore hwDiskStore = diskStores.get(i);
            Map<String, Object> hwDsMap = new ConcurrentHashMap<>();
            // 磁盘在列表中的索引位置，用于区分不同的磁盘。
            hwDsMap.put("index", i);
            // 磁盘对象的字符串表示。通常是该磁盘的简要描述信息，方便查看磁盘信息。
            hwDsMap.put("toString", String.valueOf(hwDiskStore));
            // 磁盘的名称。通常是磁盘的设备名称，如 /dev/sda 或 C: 等。
            hwDsMap.put("diskName", hwDiskStore.getName());
            // 当前磁盘的队列长度，表示有多少个请求排队等待磁盘处理。
            hwDsMap.put("currentQueueLength", hwDiskStore.getCurrentQueueLength());
            // 磁盘的型号信息，例如硬盘的生产厂家和型号。
            hwDsMap.put("model", hwDiskStore.getModel());
            // 磁盘的序列号，用于唯一标识该磁盘。
            hwDsMap.put("diskSerial", hwDiskStore.getSerial());
            // 磁盘的总容量，单位为字节。通过 FormatUtil.formatBytes() 格式化后，转换为可读性较好的单位（如 GB、MB）。
            hwDsMap.put("size", FormatUtil.formatBytes(hwDiskStore.getSize()));
            // 表示磁盘读取的数据量，单位是字节（将字节数转换为更易读的格式，如 KiB、MiB 等）。
            hwDsMap.put("reads", FormatUtil.formatBytes(hwDiskStore.getReads()));
            // 表示磁盘写入的数据量，单位是字节。
            hwDsMap.put("writes", FormatUtil.formatBytes(hwDiskStore.getWrites()));
            // 磁盘读取的总字节数。表示自系统启动以来，磁盘读取了多少数据。
            hwDsMap.put("readBytes", hwDiskStore.getReadBytes());
            // 磁盘写入的总字节数。表示自系统启动以来，磁盘写入了多少数据。
            hwDsMap.put("writeBytes", hwDiskStore.getWriteBytes());
            // 磁盘的总数据传输时间，表示磁盘在读取和写入过程中消耗的总时间（单位：毫秒）。
            hwDsMap.put("transferTime", hwDiskStore.getTransferTime());
            // 磁盘的时间戳，通常表示当前磁盘信息的采集时间。
            hwDsMap.put("timeStamp", hwDiskStore.getTimeStamp());
            // 分区信息
            List<HWPartition> partitions = hwDiskStore.getPartitions();
            List<Map<String, Object>> partitionList = new ArrayList<>(partitions.size());
            for (HWPartition partition : partitions) {
                Map<String, Object> partitionMap = new ConcurrentHashMap<>();
                // 分区对象的字符串表示，简要描述该分区的信息。
                partitionMap.put("toString", partition);
                // 分区的总容量，单位为字节。通过 FormatUtil.formatBytes() 格式化后，转换为可读性较好的单位（如 GB、MB）。
                partitionMap.put("size", FormatUtil.formatBytes(partition.getSize()));
                // 所属磁盘名称
                partitionMap.put("diskName",hwDiskStore.getName());
                // 磁盘的序列号，用于唯一标识该磁盘。
                partitionMap.put("diskSerial", hwDiskStore.getSerial());
                // 分区的名称，通常是操作系统识别该分区的名称，如 /dev/sda1、/dev/sdb1 等
                partitionMap.put("partitionName", partition.getName());
                // 分区的类型，例如 ext4、ntfs 或 FAT32 等文件系统类型。
                partitionMap.put("type", partition.getType());
                // 分区的标识符，通常是唯一的标识符，用于标识该分区。
                partitionMap.put("identification", partition.getIdentification());
                // 分区的主设备号，是操作系统用于标识磁盘设备的一个数字编号。
                partitionMap.put("major", partition.getMajor());
                // 分区的唯一标识符（UUID）。每个分区有一个唯一的 UUID，用于在不同系统或设备之间识别该分区。
                partitionMap.put("uuid", partition.getUuid());
                // 分区的挂载点，即分区在操作系统中的挂载路径。比如 /、/home、/mnt/data 等。
                partitionMap.put("mountPoint", partition.getMountPoint());
                // 分区的次设备号，用于标识同一块磁盘上的不同分区。与主设备号一起使用，用于唯一标识一个磁盘分区。
                partitionMap.put("minor", partition.getMinor());
                partitionList.add(partitionMap);
            }
            hwDsMap.put("partitionList", partitionList);
            // 给前端作为树形表格展示用字段
            hwDsMap.put("children", partitionList);
            dsList.add(hwDsMap);
        }
        return dsList;
    }

    /**
     * 电源信息读取
     *
     * @return List<Map < String, Object>
     */
    public static List<Map<String, Object>> getPowerSourceInfo() {
        List<PowerSource> powerSources = hal.getPowerSources();
        List<Map<String, Object>> powerSourcesList = new ArrayList<>(powerSources.size());
        for (PowerSource powerSource : powerSources) {
            Map<String, Object> powerSourceMap = new ConcurrentHashMap<>();
            // amperage: 电池的电流，单位是安培（A），值为 0，表示当前电流为零或无法读取。
            powerSourceMap.put("amperage", powerSource.getAmperage());
            // name: 电池的逻辑名称，值为“System Battery”，表示这是系统内置的电池。
            powerSourceMap.put("name", powerSource.getName());
            // capacityUnits: 电池容量的单位，值为 “mWh”，即毫瓦时；
            powerSourceMap.put("capacityUnits", powerSource.getCapacityUnits());
            // serialNumber: 电池的序列号，用于唯一标识这块电池；
            powerSourceMap.put("serialNumber", powerSource.getSerialNumber());
            // currentCapacity: 电池的当前容量，单位是 mWh；
            powerSourceMap.put("currentCapacity", powerSource.getCurrentCapacity());
            // deviceName: 电池设备名称，值为“RI4B02W”，用于标识电池型号或设备名；
            powerSourceMap.put("deviceName", powerSource.getDeviceName());
            // manufacturer: 电池制造商的名称，值为“SUNWODA”。
            powerSourceMap.put("manufacturer", powerSource.getManufacturer());
            // voltage: 电池电压，单位是伏特（V） ；
            powerSourceMap.put("voltage", powerSource.getVoltage());
            // chemistry: 电池化学成分，值为“LiOn”，表示锂离子电池；
            powerSourceMap.put("chemistry", powerSource.getChemistry());
            // cycleCount: 电池的循环次数，值为 0 表示该电池尚未完整充放电一次，或者信息无法读取；
            powerSourceMap.put("cycleCount", powerSource.getCycleCount());
            // powerUsageRate: 电池的实时功耗（消耗功率），值为 0，可能表示当前功耗无法读取或电池不在使用；
            powerSourceMap.put("powerUsageRate", powerSource.getPowerUsageRate());
            // designCapacity: 电池的设计容量（出厂时的理论最大容量），单位是 mWh；
            powerSourceMap.put("designCapacity", powerSource.getDesignCapacity());
            // maxCapacity: 电池的最大容量，单位是 mWh（毫瓦时）；
            powerSourceMap.put("maxCapacity", powerSource.getMaxCapacity());
            // temperature: 电池的温度，单位可能是摄氏度或华氏度，值为 0，可能表示无法读取或未测量；
            powerSourceMap.put("temperature", powerSource.getTemperature());
            // temperatureTime：实时温度的当前时间，用作图标展示；
            powerSourceMap.put("temperatureTime", DateUtil.format(LocalDateTime.now(), "HH:mm:ss"));
            // isDischarging: 表示电池是否在放电，false 表示未放电（通常指设备接入了外部电源）；
            powerSourceMap.put("isDischarging", powerSource.isDischarging());
            // isCharging: 表示电池是否在充电，false 表示当前未充电；
            powerSourceMap.put("isCharging", powerSource.isCharging());
            // isPowerOnline: 表示电源是否在线，true 表示设备接通了电源（如充电器插入）；
            powerSourceMap.put("isPowerOnLine", powerSource.isPowerOnLine());
            // timeRemainingInstant: 表示电池的即时剩余时间，单位为分钟。如果值为 -1，可能意味着电池剩余时间无法确定；
            powerSourceMap.put("timeRemainingInstant", powerSource.getTimeRemainingInstant());
            // timeRemainingEstimated: 电池剩余的估计时间（基于当前电池状态和使用情况），单位可能是分钟，-1 表示无法估算剩余时间；
            powerSourceMap.put("timeRemainingEstimated", powerSource.getTimeRemainingEstimated());
            // remainingCapacityPercent: 电池的剩余容量百分比，值为 1 表示 1%；
            powerSourceMap.put("remainingCapacityPercent", powerSource.getRemainingCapacityPercent());
            powerSourcesList.add(powerSourceMap);
        }
        return powerSourcesList;
    }


    /**
     * 获取系统服务信息，服务基于系统平台决定
     *
     * @return {@link List}
     */
    public static List<Map<String, Object>> getSystemServiceInfo() {
        final List<OSService> services = operatingSystem.getServices();
        final List<Map<String, Object>> systemServiceList = new ArrayList<>(services.size());
        for (int i = 0; i < services.size(); i++) {
            final OSService osService = services.get(i);
            final Map<String, Object> osServiceMap = new ConcurrentHashMap<>();
            osServiceMap.put("index", i);
            osServiceMap.put("toString", String.valueOf(osService));
            osServiceMap.put("state", osService.getState().name());
            osServiceMap.put("pid", osService.getProcessID());
            osServiceMap.put("name", osService.getName());
            systemServiceList.add(osServiceMap);
        }
        return systemServiceList;
    }

    /**
     * 获取传感器信息
     *
     * @return String
     */
    public static Map<String, Object> getSensorInfo() {
        final Map<String, Object> sensorInfo = new ConcurrentHashMap<>();
        final Sensors sensors = hal.getSensors();
        sensorInfo.put("toString", String.valueOf(sensors));
        sensorInfo.put("instance", sensors);
        return sensorInfo;
    }

    /**
     * 获取进程信息
     *
     * @return {@link Map}
     */
    public static Map<String, Object> getProcessesInfo() {
        GlobalMemory globalMemory = hal.getMemory();
        Map<String, Object> processesInfoMap = new ConcurrentHashMap<>();
        // 系统中当前运行的进程总数
        processesInfoMap.put("processCount", operatingSystem.getProcessCount());
        // threadCount
        processesInfoMap.put("threadCount", operatingSystem.getThreadCount());
        List<OSProcess> osProcessList = operatingSystem.getProcesses(OperatingSystem.ProcessFiltering.ALL_PROCESSES, OperatingSystem.ProcessSorting.CPU_DESC, 100);
        // osProcessMapList：一个包含多个进程信息的列表，每个进程的信息以 Map<String, Object> 的形式存储，最多包含 100 个进程，按照 CPU 使用率降序排列
        List<Map<String, Object>> osProcessMapList = new ArrayList<>(osProcessList.size());
        for (int i = 0; i < osProcessList.size(); i++) {
            OSProcess osProcess = osProcessList.get(i);
            Map<String, Object> osProcessMap = new ConcurrentHashMap<>();
            //进程在列表中的索引位置
            osProcessMap.put("index", i);
            //进程对象的字符串表示，用于简单描述该进程
            osProcessMap.put("toString", String.valueOf(osProcess));
            //进程的 PID（进程ID），是操作系统为每个进程分配的唯一标识符
            osProcessMap.put("pid", osProcess.getProcessID());
            //进程在内核模式下消耗的 CPU 时间（以毫秒为单位）
            osProcessMap.put("kernelTime", osProcess.getKernelTime());
            //进程在用户模式下消耗的 CPU 时间（以毫秒为单位）
            osProcessMap.put("userTime", osProcess.getUserTime());
            //进程从启动到当前运行的总时间（以毫秒为单位）
            osProcessMap.put("upTime", osProcess.getUpTime());
            //进程的启动时间戳（以毫秒为单位）
            osProcessMap.put("startTime", osProcess.getStartTime());
            //进程读取的字节数
            osProcessMap.put("bytesRead", osProcess.getBytesRead());
            //进程写入的字节数
            osProcessMap.put("bytesWritten", osProcess.getBytesWritten());
            //进程当前打开的文件列表
            osProcessMap.put("openFiles", osProcess.getOpenFiles());
            //进程当前允许打开的最大文件数（软限制）
            osProcessMap.put("softOpenFileLimit", osProcess.getSoftOpenFileLimit());
            //进程允许打开的最大文件数（硬限制）
            osProcessMap.put("hardOpenFileLimit", osProcess.getHardOpenFileLimit());
            //进程的累计 CPU 使用率，表示进程自启动以来消耗的 CPU 时间与总 CPU 时间的比率
            osProcessMap.put("processCpuLoadCumulative", osProcess.getProcessCpuLoadCumulative());
            //进程在上一个时间刻度（tick）之间的 CPU 使用率，反映更短时间内的 CPU 使用情况
            osProcessMap.put("processCpuLoadBetweenTicks", osProcess.getProcessCpuLoadBetweenTicks(osProcess));
            //进程的位数（32位或64位）
            osProcessMap.put("bitNess", osProcess.getBitness());
            //进程的 CPU 亲和性掩码，指示进程可以在哪些 CPU 上运行
            osProcessMap.put("affinityMask", osProcess.getAffinityMask());
            //进程发生的轻微页面错误次数（轻微页面错误表示数据不在 CPU 缓存中，但在物理内存中）
            osProcessMap.put("minorFaults", osProcess.getMinorFaults());
            //进程发生的严重页面错误次数（严重页面错误表示数据需要从磁盘读取）
            osProcessMap.put("majorFaults", osProcess.getMajorFaults());
            //进程的优先级，影响进程的 CPU 调度
            osProcessMap.put("priority", osProcess.getPriority());
            //进程当前运行的线程数
            osProcessMap.put("threadCount", osProcess.getThreadCount());
            //进程所属的组名
            osProcessMap.put("group", osProcess.getGroup());
            //进程所属的组 ID
            osProcessMap.put("groupId", osProcess.getGroupID());
            //进程运行的用户名称
            osProcessMap.put("user", osProcess.getUser());
            //进程运行的用户 ID
            osProcessMap.put("userId", osProcess.getUserID());
            //进程的当前工作目录
            osProcessMap.put("currentWorkingDirectory", osProcess.getCurrentWorkingDirectory());
            //进程的可执行文件路径
            osProcessMap.put("path", osProcess.getPath());
            //启动进程时传递的命令行参数
            osProcessMap.put("arguments", osProcess.getArguments());
            //进程的环境变量
            osProcessMap.put("environmentVariables", osProcess.getEnvironmentVariables());
            //进程的 CPU 使用率
            osProcessMap.put("cpuUsageRate", 100d * (osProcess.getKernelTime() + osProcess.getUserTime()) / osProcess.getUpTime());
            //进程的内存使用率
            osProcessMap.put("memUsageRate", 100d * osProcess.getResidentSetSize() / globalMemory.getTotal());
            //进程使用的虚拟内存大小，经过格式化以可读方式显示（如 MB 或 GB）
            osProcessMap.put("virtualMemSize", FormatUtil.formatBytes(osProcess.getVirtualSize()));
            //进程的常驻集大小（RSS），表示进程实际占用的物理内存大小，也经过格式化显示。
            osProcessMap.put("residentSetSize", FormatUtil.formatBytes(osProcess.getResidentSetSize()));
            //进程的名称。
            osProcessMap.put("processName", osProcess.getName());
            osProcessMapList.add(osProcessMap);
        }
        processesInfoMap.put("osProcessMapList", osProcessMapList);
        return processesInfoMap;
    }

    /**
     * 获取内存信息
     *
     * @return {@link Map}
     */
    public static Map<String, Object> getMemoryInfo() {
        GlobalMemory globalMemory = hal.getMemory();
        Map<String, Object> gmMap = new ConcurrentHashMap<>();
        // 系统总运行内存的大小，可以转换成可读的字节数（GB、MB）
        gmMap.put("total", FormatUtil.formatBytes(globalMemory.getTotal()));
        // 系统可用运行内存的大小，可以转换成可读的字节数（GB、MB）
        gmMap.put("available", FormatUtil.formatBytes(globalMemory.getAvailable()));
        // 系统已用内存大小，可以转换成可读的字节数（GB、MB）
        gmMap.put("used", FormatUtil.formatBytes(globalMemory.getTotal() - globalMemory.getAvailable()));
        // 系统内存的使用率，表示已用内存占总内存的比例，计算方式： (总内存 - 可用内存) / 总内存 * 100
        gmMap.put("usageRate", 100d * (globalMemory.getTotal() - globalMemory.getAvailable()) / globalMemory.getTotal());
        // 当前时间的格式化表示，格式为“mm:ss”，代表当前内存使用率的时间戳
        gmMap.put("usageRateTime", DateUtil.format(LocalDateTime.now(), "HH:mm"));
        // 内存页面大小，即操作系统管理内存的基本单元大小
        gmMap.put("pageSize", globalMemory.getPageSize());
        // 虚拟内存的详细信息
        VirtualMemory virtualMemory = globalMemory.getVirtualMemory();
        Map<String, Object> vmMap = new ConcurrentHashMap<>();
        // 虚拟内存的字符串表示
        vmMap.put("toString", virtualMemory);
        // 虚拟内存交换空间的总大小，格式化为可读的字节数
        vmMap.put("swapTotal", FormatUtil.formatBytes(virtualMemory.getSwapTotal()));
        // 当前已使用的交换空间，格式化为可读的字节数
        vmMap.put("swapUsed", FormatUtil.formatBytes(virtualMemory.getSwapUsed()));
        // 交换空间的使用率，计算方式为 (已使用的交换空间 / 交换空间总大小) * 100
        vmMap.put("swapUsageRate", 100d * virtualMemory.getSwapUsed() / virtualMemory.getSwapTotal());
        // 虚拟内存的最大大小，格式化为可读的字节数
        vmMap.put("virtualMax", FormatUtil.formatBytes(virtualMemory.getVirtualMax()));
        // 当前已使用的虚拟内存大小，格式化为可读的字节数
        vmMap.put("virtualInUse", FormatUtil.formatBytes(virtualMemory.getVirtualInUse()));
        // 虚拟内存的使用率，计算方式为 (已使用的虚拟内存 / 虚拟内存最大值) * 100
        vmMap.put("virtualUsageRate", 100d * virtualMemory.getVirtualInUse() / virtualMemory.getVirtualMax());
        gmMap.put("virtualMemory", vmMap);
        // 物理内存的详细信息列表
        List<PhysicalMemory> physicalMemoryList = globalMemory.getPhysicalMemory();
        List<Map<String, Object>> pmInfoList = new ArrayList<>(physicalMemoryList.size());
        for (PhysicalMemory pm : physicalMemoryList) {
            Map<String, Object> pmMap = new ConcurrentHashMap<>();
            // 物理内存的字符串表示
            pmMap.put("toString", String.valueOf(pm));
            // 物理内存模块的银行标签（例如：DIMM槽编号）
            pmMap.put("bankLabel", pm.getBankLabel());
            // 内存制造商的名称
            pmMap.put("manufacturer", pm.getManufacturer());
            // 物理内存模块的大小，格式化为可读的字节数
            pmMap.put("capacity", FormatUtil.formatBytes(pm.getCapacity()));
            // 内存类型（如DDR4、DDR3等）
            pmMap.put("memoryType", pm.getMemoryType());
            // 内存的时钟频率，格式化为赫兹（Hz）
            pmMap.put("clockSpeed", FormatUtil.formatHertz(pm.getClockSpeed()));
            pmInfoList.add(pmMap);
        }
        gmMap.put("physicalMemoryList", pmInfoList);
        return gmMap;
    }

    /**
     * 获取操作系统信息
     *
     * @return {@link Map}
     */
    public static Map<String, Object> getOperateSystemInfo() {
        Map<String, Object> osInfo = new ConcurrentHashMap<>();
        osInfo.put("osName", String.valueOf(operatingSystem));
        osInfo.put("booted", Instant.ofEpochSecond(operatingSystem.getSystemBootTime()));
        osInfo.put("sessionList", operatingSystem.getSessions());
        ComputerSystem computerSystem = hal.getComputerSystem();
        osInfo.put("computerSystem", String.valueOf(computerSystem));
        osInfo.put("firmware: ", computerSystem.getFirmware());
        osInfo.put("baseboard: ", computerSystem.getBaseboard());
        return osInfo;
    }

    /**
     * 获取CPU信息
     *
     * @return {@link Map}
     */
    public static Map<String, Object> getCpuInfo() {
        Map<String, Object> cpuInfo = new ConcurrentHashMap<>();
        CentralProcessor processor = hal.getProcessor();
        cpuInfo.put("toString", String.valueOf(processor));
        cpuInfo.put("instance", processor);
        return cpuInfo;
    }

    /**
     * 读取显示器信息
     *
     * @return {@link List}
     */
    public static List<Map<String, Object>> getDisplayInformation() {
        List<Display> displays = hal.getDisplays();
        List<Map<String, Object>> displayInfos = new ArrayList<>(displays.size());
        for (int i = 0; i < displays.size(); i++) {
            Map<String, Object> displayInfo = new ConcurrentHashMap<>();
            displayInfo.put("index", String.valueOf(i));
            final Display display = displays.get(i);
            byte[] edidBytes = display.getEdid();
            displayInfo.put("manufacturerId", EdidUtil.getManufacturerID(edidBytes));
            displayInfo.put("productId", EdidUtil.getProductID(edidBytes));
            displayInfo.put("isDigital", EdidUtil.isDigital(edidBytes) ? "Digital" : "Analog");
            displayInfo.put("serialNo", EdidUtil.getSerialNo(edidBytes));
            displayInfo.put("manufacturerDate", (EdidUtil.getWeek(edidBytes) * 12 / 52 + 1) + '/' + EdidUtil.getYear(edidBytes));
            displayInfo.put("version", EdidUtil.getVersion(edidBytes));
            displayInfo.put("cmHeight", EdidUtil.getHcm(edidBytes));
            displayInfo.put("cmWidth", EdidUtil.getVcm(edidBytes));
            displayInfo.put("inHeight", EdidUtil.getHcm(edidBytes) / 2.54);
            displayInfo.put("inWidth", EdidUtil.getVcm(edidBytes) / 2.54);
            displayInfo.put("toString", String.valueOf(display));
            byte[][] descriptorBytes = EdidUtil.getDescriptors(edidBytes);
            for (byte[] bytes : descriptorBytes) {
                switch (EdidUtil.getDescriptorType(bytes)) {
                    case 0xff:
                        displayInfo.put("serialNumber", EdidUtil.getDescriptorText(bytes));
                        break;
                    case 0xfe:
                        displayInfo.put("unspecifiedText", EdidUtil.getDescriptorText(bytes));
                        break;
                    case 0xfd:
                        displayInfo.put("rangeLimits", EdidUtil.getDescriptorRangeLimits(bytes));
                        break;
                    case 0xfc:
                        displayInfo.put("monitorName", EdidUtil.getDescriptorText(bytes));
                        break;
                    case 0xfb:
                        displayInfo.put("whitePointData", ParseUtil.byteArrayToHexString(bytes));
                        break;
                    case 0xfa:
                        displayInfo.put("standardTimingId", ParseUtil.byteArrayToHexString(bytes));
                        break;
                    default:
                        if (EdidUtil.getDescriptorType(bytes) <= 0x0f && EdidUtil.getDescriptorType(bytes) >= 0x00) {
                            displayInfo.put("manufacturerData", ParseUtil.byteArrayToHexString(bytes));
                        } else {
                            displayInfo.put("preferredTiming", EdidUtil.getTimingDescriptor(bytes));
                        }
                }
            }
            displayInfos.add(displayInfo);
        }
        return displayInfos;
    }

    /**
     * 获取操作 JVM 信息
     *
     * @return {@link Map}
     */
    public static Map<String, Object> getJvmInfo() {
        Properties properties = System.getProperties();
        Map<String, Object> jvmInfo = new ConcurrentHashMap<>();
        // Java 的完整版本号
        jvmInfo.put("version", properties.getProperty("java.version"));
        // 类文件版本号
        jvmInfo.put("classVersion", properties.getProperty("java.class.version"));
        // JVM 的版本号
        jvmInfo.put("jvmVersion", properties.getProperty("java.vm.version"));
        // JVM 的提供商
        jvmInfo.put("jvmVendor", properties.getProperty("java.vm.vendor"));
        // Jvm 提供商地址
        jvmInfo.put("jvmVendorUrl", properties.getProperty("java.vendor.url"));
        // Jvm 平台规范提供商
        jvmInfo.put("jvmSpecificationVendor", properties.getProperty("java.specification.vendor"));
        // Jvm 平台规范版本
        jvmInfo.put("jvmSpecificationVersion", properties.getProperty("java.vm.specification.version"));
        // Jvm 平台规范名称
        jvmInfo.put("jvmSpecificationName", properties.getProperty("java.vm.specification.name"));
        // Java 平台规范版本
        jvmInfo.put("javaSpecificationVersion", properties.getProperty("java.specification.version"));
        // Java 平台 API 规范
        jvmInfo.put("javaSpecificationName", properties.getProperty("java.specification.name"));
        // 执行命令
        jvmInfo.put("execCommand", properties.getProperty("sun.java.command"));
        // JVM使用的编译器，HotSpot 64-Bit Tiered Compilers为 HotSpot 的分层编译器
        jvmInfo.put("compiler", properties.getProperty("sun.management.compiler"));
        // JVM 名称，Java HotSpot(TM) 64-Bit Server VM 是 HotSpot 64 位服务器虚拟机
        jvmInfo.put("jvmName", properties.getProperty("java.vm.name"));
        // Jvm 模式
        jvmInfo.put("jvmMode", properties.getProperty("java.vm.info"));
        // 架构模型
        jvmInfo.put("archDataModel", properties.getProperty("sun.arch.data.model"));
        // Java 运行时的完整版本号
        jvmInfo.put("runtimeVersion", properties.getProperty("java.runtime.version"));
        // 运行时环境名称
        jvmInfo.put("runtimeName", properties.getProperty("java.runtime.name"));
        // Java 安装路径
        jvmInfo.put("home", properties.getProperty("java.home"));
        // 获取 JVM 本地库的搜索路径
        jvmInfo.put("libPath", properties.getProperty("java.library.path").split(";"));
        // 获取 JVM 的类路径，并按分号分隔为数组
        jvmInfo.put("classPath", properties.getProperty("java.class.path").split(";"));
        // 获取 JVM 启动所需的库路径（即 JRE 的 bin 目录路径）
        jvmInfo.put("jreBinPath", properties.getProperty("sun.boot.library.path"));
        // 获取 JVM 启动类路径，并按分号分隔为数组
        jvmInfo.put("bootClassPath", properties.getProperty("sun.boot.class.path").split(";"));
        // JVM 启动器的类型
        jvmInfo.put("launcher", properties.getProperty("sun.java.launcher"));
        // 获取扩展目录路径，并按分号分隔为数组
        jvmInfo.put("extendsDir", properties.getProperty("java.ext.dirs").split(";"));
        // 临时文件存储目录
        jvmInfo.put("ioTmpDir", properties.getProperty("java.io.tmpdir"));
        // 启动时间
        long startTime = ManagementFactory.getRuntimeMXBean().getStartTime();
        LocalDateTime start = Instant.ofEpochMilli(startTime).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        jvmInfo.put("startTime", DateUtil.formatLocalDateTime(start));
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(start, now);
        long totalSeconds = duration.getSeconds();
        long days = totalSeconds / (24 * 60 * 60);
        long hours = (totalSeconds % (24 * 60 * 60)) / (60 * 60);
        long minutes = (totalSeconds % (60 * 60)) / 60;
        long seconds = totalSeconds % 60;
        Map<String, Object> durationInfo = new ConcurrentHashMap<>();
        durationInfo.put("day", days);
        durationInfo.put("hour", hours);
        durationInfo.put("min", minutes);
        durationInfo.put("sec", seconds);
        // 运行时长
        jvmInfo.put("duration", durationInfo);
        return jvmInfo;
    }

    /**
     * 获取JVM内存信息
     *
     * @return Map<String, Object>
     */
    public static Map<String, Object> getJvmMemory() {
        Runtime runtime = Runtime.getRuntime();
        Map<String, Object> jvmMemory = new ConcurrentHashMap<>();
        long totalMem = runtime.totalMemory();
        long freeMem = runtime.freeMemory();
        long usedMem = totalMem - freeMem;
        long maxMem = runtime.maxMemory();
        jvmMemory.put("total", FormatUtil.formatBytes(totalMem));
        jvmMemory.put("max", FormatUtil.formatBytes(maxMem));
        jvmMemory.put("free", FormatUtil.formatBytes(freeMem));
        jvmMemory.put("used", FormatUtil.formatBytes(usedMem));
        jvmMemory.put("usageRate", String.format("%.2f", 100D * usedMem / totalMem) + "%");
        jvmMemory.put("freeRate", String.format("%.2f", 100D * freeMem / totalMem) + "%");
        return jvmMemory;
    }

    /**
     * 获取系统信息
     *
     * @return {@link Map}
     */
    public static Map<String, Object> getSystemInfo() {
        Map<String, Object> serverSysInfo = new ConcurrentHashMap<>();
        Properties properties = System.getProperties();
        // 操作系统名称
        serverSysInfo.put("osName", properties.getProperty("os.name"));
        // 操作系统的版本号
        serverSysInfo.put("osVersion", properties.getProperty("os.version"));
        // 操作系统架构
        serverSysInfo.put("osArch", properties.getProperty("os.arch"));
        // 用户的工作目录路径
        serverSysInfo.put("userDir", properties.getProperty("user.dir"));
        // 用户的主目录路径
        serverSysInfo.put("userHome", properties.getProperty("user.home"));
        // 当前操作系统的用户名
        serverSysInfo.put("userName", properties.getProperty("user.name"));
        // fixme 用户变量（容器环境无访问权限）
        // serverSysInfo.put("userVariant", getSystemProperty("user.variant"));
        // 用户的时区，Asia/Shanghai 表示上海时区（容器环境无访问权限）
        serverSysInfo.put("userTimezone", getSystemProperty("user.timezone"));
        // 用户的语言设置，zh 表示中文（容器环境无访问权限）
        serverSysInfo.put("userLanguage", getSystemProperty("user.language"));
        /*
         * 系统环境变量
         * USERDOMAIN_ROAMINGPROFILE：表示用户的域名；
         * PROCESSOR_LEVEL： 处理器级别；
         * SESSIONNAME： 当前会话的名称，Console 表示是本地控制台会话；
         * JAVA_HOME： Java安装目录路径；
         * MAVEN_HOME：Maven安装目录；
         * PATH：系统的PATH环境变量，包含了可以被执行的命令或程序的路径；
         * TNS_ADMIN：Oracle的网络配置文件路径，表示Oracle连接配置的路径；
         * ORACLE_HOME：Oracle客户端的安装路径；
         * ANDROID_SDK_ROOT：Android SDK的安装路径，表示Android开发环境的路径；
         * TEMP/TMP：临时文件的存放路径，用于存储临时数据；
         * CLASSPATH：Java类路径，表示在执行Java应用程序时需要的类文件位置；
         * OS：操作系统类型，Windows_NT 表示Windows操作系统；
         * COMPUTERNAME：计算机名，表示计算机的名称；
         * NUMBER_OF_PROCESSORS：处理器的核心数，8 表示有8个处理器核心；
         * LOGONSERVER：登录服务器，表示域登录的服务器；
         * PUBLIC：公共文件目录；
         * NVM_HOME：Node Version Manager的安装路径；
         * ZES_ENABLE_SYSMAN：ZES系统管理工具的启用状态，值为1表示启用；
         * CLION_VM_OPTIONS：表示CLion的虚拟机配置文件路径；
         * SVN_EXPERIMENTAL_COMMANDS：SVN（Subversion）的实验性命令设置，值为shelf2；
         * IDEA_VM_OPTIONS：表示IntelliJ IDEA的虚拟机配置文件路径；
         * PYCHARM_VM_OPTIONS：表示PyCharm的虚拟机配置文件路径；
         * HOMEPATH/HOMEDRIVE：用户主目录的路径；
         * ProgramFiles：64位程序的安装目录；
         * APPDATA：用户的应用数据目录，存储应用程序的配置文件和数据；
         */
        serverSysInfo.put("sysEnv", System.getenv());
        return serverSysInfo;
    }

    /**
     * 针对部分容器环境，对系统的属性无权访问
     *
     * @param key 键
     * @return {@link Object}
     */
    protected static Object getSystemProperty(String key) {
        Object val;
        Properties properties = System.getProperties();
        try {
            val = properties.getProperty(key);
        } catch (Exception e) {
            val = "无访问权限";
        }
        return val;
    }
}
