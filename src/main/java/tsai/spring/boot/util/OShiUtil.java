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
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
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
        List<SoundCard> soundCards = hal.getSoundCards();
        List<Map<String, Object>> soundCardInfos = new ArrayList<>(soundCards.size());
        for (int i = 0; i < soundCards.size(); i++) {
            Map<String, Object> soundCard = new ConcurrentHashMap<>();
            //声卡在列表中的索引位置，用于标识该声卡在返回列表中的顺序。
            soundCard.put("index", String.valueOf(i));
            SoundCard soundCardInst = soundCards.get(i);
            //声卡的字符串表示，通常是该声卡的简要描述信息。
            soundCard.put("toString", String.valueOf(soundCardInst));
            //声卡的编解码器（Codec），即声卡所使用的音频编解码标准或技术。
            soundCard.put("codec", soundCardInst.getCodec());
            //声卡的名称，通常是声卡的品牌和型号名称。
            soundCard.put("name", soundCardInst.getName());
            //声卡驱动程序的版本号，指示当前安装的驱动程序版本。
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
        List<GraphicsCard> graphicsCards = hal.getGraphicsCards();
        List<Map<String, Object>> graphicsCardsInfos = new ArrayList<>(graphicsCards.size());
        for (int i = 0; i < graphicsCards.size(); i++) {
            Map<String, Object> gcInfo = new ConcurrentHashMap<>();
            //显卡在列表中的索引位置，用于标识该显卡在返回列表中的顺序。
            gcInfo.put("index", String.valueOf(i));
            GraphicsCard graphicsCard = graphicsCards.get(i);
            //显卡的字符串表示，通常是该显卡的简要描述信息。
            gcInfo.put("toString", String.valueOf(graphicsCard));
            //显卡的名称，通常是显卡的品牌和型号名称。
            gcInfo.put("name", graphicsCard.getName());
            //显卡的设备 ID，通常是唯一标识显卡的一个标识符。
            gcInfo.put("deviceId", graphicsCard.getDeviceId());
            //显卡的版本信息，通常是显卡驱动程序的版本信息，或显卡硬件的版本信息。
            gcInfo.put("versionInfo", graphicsCard.getVersionInfo());
            //显卡的制造商名称，例如 NVIDIA、AMD 或 Intel。
            gcInfo.put("vendor", graphicsCard.getVendor());
            //显卡的显存大小，通常以字节为单位，表示显卡用于存储图形数据的内存大小。
            gcInfo.put("vRam", graphicsCard.getVRam());
            //显卡的显存大小，格式化为人类易读的单位，如 KB、MB、GB 等。
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
        List<UsbDevice> usbDevices = hal.getUsbDevices(true);
        List<Map<String, Object>> usbDevicesInfos = new ArrayList<>(usbDevices.size());
        for (int i = 0; i < usbDevices.size(); i++) {
            Map<String, Object> usbDeviceInfo = new ConcurrentHashMap<>();
            //设备在列表中的索引位置，用于标识该设备在返回的列表中的位置。
            usbDeviceInfo.put("index", String.valueOf(i));
            UsbDevice usbDevice = usbDevices.get(i);
            //USB 设备的字符串表示，通常是该设备的简要描述信息。
            usbDeviceInfo.put("toString", String.valueOf(usbDevice));
            //USB 设备的名称。通常是设备的用户友好名称，例如“USB Flash Drive”、“USB Keyboard”等。
            usbDeviceInfo.put("name", usbDevice.getName());
            //USB 设备的唯一标识符。这个标识符可以唯一地标识每个 USB 设备，通常是一个字符串或数值。
            usbDeviceInfo.put("uniqueDeviceId", usbDevice.getUniqueDeviceId());
            //USB 设备的厂商名称。标识该设备制造商的字符串，例如“SanDisk”、“Logitech”。
            usbDeviceInfo.put("vendor", usbDevice.getVendor());
            //USB 设备的产品 ID。这个 ID 用于标识该 USB 设备的具体型号。
            usbDeviceInfo.put("productId", usbDevice.getProductId());
            //USB 设备的序列号。每个设备都有一个唯一的序列号，可以用于区分同一厂商生产的不同设备。
            usbDeviceInfo.put("serialNumber", usbDevice.getSerialNumber());
            //USB 设备的厂商 ID，通常是一个十六进制的数字，代表设备制造商的身份。
            usbDeviceInfo.put("vendorId", usbDevice.getVendorId());
            //当前 USB 设备连接的其他设备列表。返回该 USB 设备直接连接的所有设备（如果有的话）。通常这些是通过 USB 链接到主设备的外部设备。
            List<UsbDevice> connectedDevices = usbDevice.getConnectedDevices();
            usbDeviceInfo.put("connectedDevices", connectedDevices);
            // 用作前端树表格展示数据
            usbDeviceInfo.put("children", connectedDevices);
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
        //网络参数对象的字符串表示，通常会显示网络参数对象的简要描述。
        networkParamsInfo.put("toString", networkParams);
        //系统的主机名，表示计算机在网络中的标识符。
        networkParamsInfo.put("hostName", networkParams.getHostName());
        //系统的 DNS 服务器列表，用于解析域名到 IP 地址。返回的是一个 DNS 服务器的地址数组，通常是一个或多个 IP 地址。
        networkParamsInfo.put("dnsServers", Arrays.asList(networkParams.getDnsServers()));
        //系统的域名，通常表示该计算机所属的域，可能是一个本地网络域名或外部域名。
        networkParamsInfo.put("domainName", networkParams.getDomainName());
        //系统的 IPv4 默认网关，用于计算机与其他网络之间的通信。网关地址是一个 IP 地址，通常是路由器或防火墙的 IP 地址。
        networkParamsInfo.put("ipv4Gateway", networkParams.getIpv4DefaultGateway());
        //系统的 IPv6 默认网关，与 ipv4Gateway 类似，但针对的是 IPv6 网络。该网关用于计算机与 IPv6 网络之间的通信。
        networkParamsInfo.put("ipv6Gateway", networkParams.getIpv6DefaultGateway());
        return networkParamsInfo;
    }

    /**
     * IP 信息统计
     *
     * @return {@link Map}
     */
    public static Map<String, Object> getIpStatistics() {
        InternetProtocolStats internetProtocolStats = operatingSystem.getInternetProtocolStats();
        Map<String, Object> ipStatisticsMap = new ConcurrentHashMap<>();
        // 统计 TCP v4 信息
        Map<String, Object> tcpV4 = new ConcurrentHashMap<>();
        InternetProtocolStats.TcpStats tcPv4Stats = internetProtocolStats.getTCPv4Stats();
        getTcpInformation(tcpV4, tcPv4Stats);
        ipStatisticsMap.put("tcpV4", tcpV4);
        // 统计 TCP v6 信息
        Map<String, Object> tcpV6 = new ConcurrentHashMap<>();
        InternetProtocolStats.TcpStats tcPv6Stats = internetProtocolStats.getTCPv6Stats();
        getTcpInformation(tcpV6, tcPv6Stats);
        ipStatisticsMap.put("tcpV6", tcpV6);
        // 统计 UDP v4 信息
        Map<String, Object> udpV4 = new ConcurrentHashMap<>();
        InternetProtocolStats.UdpStats udPv4Stats = internetProtocolStats.getUDPv4Stats();
        getUdpInformation(udpV4, udPv4Stats);
        ipStatisticsMap.put("udpV4", udpV4);
        // 统计 UDP v6 信息
        Map<String, Object> udpV6 = new ConcurrentHashMap<>();
        InternetProtocolStats.UdpStats udPv6Stats = internetProtocolStats.getUDPv6Stats();
        getUdpInformation(udpV6, udPv6Stats);
        ipStatisticsMap.put("udpV6", udpV6);
        // 网络连接信息
        List<InternetProtocolStats.IPConnection> connections = internetProtocolStats.getConnections();
        List<Map<String, Object>> ipConnectionInfoList = new ArrayList<>(connections.size());
        for (int i = 0; i < connections.size(); i++) {
            Map<String, Object> ipConnectionInfo = new ConcurrentHashMap<>();
            //连接在列表中的索引
            ipConnectionInfo.put("connectionIndex", i);
            InternetProtocolStats.IPConnection ipConnection = connections.get(i);
            //连接对象的字符串表示。
            ipConnectionInfo.put("toString", String.valueOf(ipConnection));
            //远程地址
            ipConnectionInfo.put("foreignAddress", convertBytesToIp(ipConnection.getForeignAddress()));
            //远程端口
            ipConnectionInfo.put("foreignPort", ipConnection.getForeignPort());
            //本地地址
            ipConnectionInfo.put("localAddress", convertBytesToIp(ipConnection.getLocalAddress()));
            //连接的当前状态
            ipConnectionInfo.put("state", ipConnection.getState());
            //连接的类型（例如 TCP 或 UDP）。
            ipConnectionInfo.put("type", ipConnection.getType());
            //本地端口
            ipConnectionInfo.put("localPort", ipConnection.getLocalPort());
            //拥有该连接的进程 ID。
            ipConnectionInfo.put("owningProcessId", ipConnection.getowningProcessId());
            //接收队列大小
            ipConnectionInfo.put("receiveQueue", ipConnection.getReceiveQueue());
            //发送队列大小
            ipConnectionInfo.put("transmitQueue", ipConnection.getTransmitQueue());
            ipConnectionInfoList.add(ipConnectionInfo);
        }
        ipStatisticsMap.put("ipConnections", ipConnectionInfoList);
        return ipStatisticsMap;
    }

    /**
     * 返回标准的IP地址
     * @param address
     * @return {@link String}
     */
    protected static String convertBytesToIp(byte[] address) {
        try {
            InetAddress inetAddress = InetAddress.getByAddress(address);
            return inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            return "Unknown";
        }
    }

    /**
     * 封装TCP信息
     *
     * @param tcp      tcp
     * @param tcpStats tcp实例
     */
    protected static void getTcpInformation(Map<String, Object> tcp, InternetProtocolStats.TcpStats tcpStats) {
        //tcpStats 对象的字符串表示形式
        tcp.put("toString", String.valueOf(tcpStats));
        //统计连接失败的次数。连接失败可能是由于网络不稳定、目标不可达、服务器拒绝连接等原因。
        tcp.put("connectionFailures", tcpStats.getConnectionFailures());
        //当前系统中的活动 TCP 连接数量。活动连接是指正在进行数据传输或等待数据传输的连接。
        tcp.put("connectionsActive", tcpStats.getConnectionsActive());
        //当前系统中被动等待的连接数量。被动连接通常是那些正在等待请求的服务器端连接（如处于监听状态的连接）。
        tcp.put("connectionsPassive", tcpStats.getConnectionsPassive());
        //TCP 连接重置的次数。连接重置是指一方在连接过程中发送了一个“RST”报文，通常是因为连接遇到错误或被强制关闭。
        tcp.put("connectionsReset", tcpStats.getConnectionsReset());
        //当前已经建立的连接数。表示系统中正在进行正常数据传输的 TCP 连接。
        tcp.put("connectionsEstablished", tcpStats.getConnectionsEstablished());
        //接收的 TCP 数据包中的错误数量。可能包括无效的数据包、校验和错误、格式错误等问题。
        tcp.put("inErrors", tcpStats.getInErrors());
        //发送的 TCP 连接重置请求次数。即系统主动发送的 "RST" 报文的次数，通常表示主动关闭连接或异常终止。
        tcp.put("outResets", tcpStats.getOutResets());
        //系统接收到的 TCP 数据段的数量。TCP 数据段是通过网络传输的数据包的基本单位。
        tcp.put("segmentsReceived", tcpStats.getSegmentsReceived());
        //重传的 TCP 数据段数量。TCP 会进行重传，特别是在数据丢失或确认延迟的情况下。
        tcp.put("segmentsRetransmitted", tcpStats.getSegmentsRetransmitted());
        //系统发送的 TCP 数据段数量。表示从本地系统发送到远端系统的 TCP 数据包的数量。
        tcp.put("segmentsSent", tcpStats.getSegmentsSent());
    }

    /**
     * 封装UDP信息
     *
     * @param udp      udp
     * @param udpStats udp实例
     */
    protected static void getUdpInformation(Map<String, Object> udp, InternetProtocolStats.UdpStats udpStats) {
        //udpStats 对象的字符串表示形式
        udp.put("toString", String.valueOf(udpStats));
        //接收到的没有对应端口的 UDP 数据报的数量。当 UDP 数据报没有指定有效的端口号时，会被计入这个统计。
        //例如，如果数据报的目标端口不存在或者不可达，那么该数据报就没有有效的目标端口。
        udp.put("datagramsNoPort", udpStats.getDatagramsNoPort());
        //系统接收到的 UDP 数据报的总数量。UDP 数据报是通过网络传输的最小单位，不同于 TCP，UDP 是无连接的，因此每个数据报都是独立处理的。
        udp.put("datagramsReceived", udpStats.getDatagramsReceived());
        //接收到的 UDP 数据报中的错误数量。可能是由于数据报的格式错误、校验和错误或其他网络错误导致的数据报无法正确处理。
        udp.put("datagramsReceivedErrors", udpStats.getDatagramsReceivedErrors());
        //系统发送的 UDP 数据报的总数量。表示从本地系统发送到远程系统的 UDP 数据包的数量。UDP 是一个无连接协议，因此每个发送的数据报都是独立的，不需要建立连接。
        udp.put("datagramsSent", udpStats.getDatagramsSent());
    }

    /**
     * 网络接口信息读取
     *
     * @return {@link List}
     */
    public static List<Map<String, Object>> getNetWorkInterfaces() {
        List<NetworkIF> networkIFs = hal.getNetworkIFs();
        List<Map<String, Object>> networkIFList = new ArrayList<>(networkIFs.size());
        for (int i = 0; i < networkIFs.size(); i++) {
            Map<String, Object> networkIfMap = new ConcurrentHashMap<>();
            //网络接口在列表中的索引位置，用于区分不同的网络接口。
            networkIfMap.put("index", i);
            NetworkIF networkIF = networkIFs.get(i);
            //网络接口对象的字符串表示。通常是该网络接口的简要描述信息，便于查看接口的整体信息。
            networkIfMap.put("toString", String.valueOf(networkIF));
            //网络接口的显示名称，通常是用户友好的接口名称（例如：Ethernet0、Wi-Fi 等）。
            networkIfMap.put("displayName", networkIF.getDisplayName());
            //网络接口的名称，操作系统层面的接口名称（例如：eth0、wlan0 等）。
            networkIfMap.put("name", networkIF.getName());
            //网络接口的索引编号，用于标识该接口。
            networkIfMap.put("ifIndex", networkIF.getIndex());
            //网络接口的 IPv4 地址，通常是一个字符串，表示该接口的 IPv4 地址（如 192.168.1.1）。
            networkIfMap.put("iPv4addr", networkIF.getIPv4addr());
            //网络接口的 IPv6 地址，通常是一个字符串，表示该接口的 IPv6 地址（如 fe80::1）。
            networkIfMap.put("iPv6addr", networkIF.getIPv6addr());
            //网络接口的 MAC 地址，唯一标识该接口的硬件地址。
            networkIfMap.put("macAddr", networkIF.getMacaddr());
            //网络接口的速率，单位是比特每秒（bps），表示该接口的最大传输速率。
            networkIfMap.put("speed", networkIF.getSpeed());
            //网络接口的子网掩码，通常是一个 IPv4 地址，表示该网络接口的子网掩码。
            networkIfMap.put("subnetMasks", networkIF.getSubnetMasks());
            //网络接口接收到的字节数，表示自系统启动以来该接口接收到的总数据量（字节）。
            networkIfMap.put("bytesReceive", networkIF.getBytesRecv());
            //网络接口发送的字节数，表示自系统启动以来该接口发送的总数据量（字节）。
            networkIfMap.put("bytesSent", networkIF.getBytesSent());
            //网络接口发生的冲突次数。此字段用于描述数据包冲突的次数，通常发生在共享介质（如旧式以太网）中。
            networkIfMap.put("collisions", networkIF.getCollisions());
            //网络接口的别名，可以是描述该接口的名称或标识符。
            networkIfMap.put("ifAlias", networkIF.getIfAlias());
            //网络接口的操作状态，表示接口的当前状态，如 UP（启用）、DOWN（禁用）、TESTING（测试中）等。
            networkIfMap.put("ifOperStatus", networkIF.getIfOperStatus().name());
            //网络接口的类型，如 ethernet、wifi 等。
            networkIfMap.put("ifType", networkIF.getIfType());
            //接收数据包丢失的数量。表示由于缓冲区溢出等原因，该接口丢弃的接收数据包数量。
            networkIfMap.put("inDrops", networkIF.getInDrops());
            //接收数据包错误的数量，表示该接口接收到的数据包中发生错误的数量。
            networkIfMap.put("inErrors", networkIF.getInErrors());
            //最大传输单元（Maximum Transmission Unit），表示网络接口能够处理的最大数据包大小（单位：字节）。
            networkIfMap.put("mtu", networkIF.getMTU());
            //网络接口的物理介质类型，表示该接口使用的物理连接类型，例如 Ethernet、Wi-Fi、Fibre Channel 等。
            networkIfMap.put("ndisPhysicalMediumType", networkIF.getNdisPhysicalMediumType());
            //发送数据包错误的数量，表示该接口发送的数据包中发生错误的数量。
            networkIfMap.put("outErrors", networkIF.getOutErrors());
            //网络接口接收到的总数据包数量。
            networkIfMap.put("packetsReceive", networkIF.getPacketsRecv());
            //网络接口发送的总数据包数量。
            networkIfMap.put("packetsSent", networkIF.getPacketsSent());
            //网络接口的前缀长度，表示子网掩码的前缀长度（例如 IPv6 地址的前缀长度）。
            networkIfMap.put("prefixLengths", networkIF.getPrefixLengths());
            //网络接口数据采集的时间戳，用于记录该信息的采集时间。
            networkIfMap.put("timeStamp", networkIF.getTimeStamp());
            //判断该接口是否已连接到物理介质（如网线、Wi-Fi 等）。
            networkIfMap.put("isConnectorPresent", networkIF.isConnectorPresent());
            //判断该接口是否是已知虚拟机的 MAC 地址，通常用于虚拟化环境中。
            networkIfMap.put("isKnownVmMacAddr", networkIF.isKnownVmMacAddr());
            //获取该网络接口的 NetworkInterface 对象的字符串表示。
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
        Map<String, Object> fsInfo = new ConcurrentHashMap<>();
        FileSystem fileSystem = operatingSystem.getFileSystem();
        List<Map<String, Object>> fileSystemInfos = new ArrayList<>();
        //当前操作系统打开的文件描述符数量。文件描述符是操作系统用于跟踪和管理已打开文件的标识符。操作系统通常对文件描述符的数量有一定限制。
        fsInfo.put("openFileDescriptors", fileSystem.getOpenFileDescriptors());
        //操作系统允许的最大文件描述符数量。它是操作系统对每个进程或整个系统的文件打开数目所做的限制。
        fsInfo.put("maxFileDescriptors", fileSystem.getMaxFileDescriptors());
        //一个格式化的字符串，显示当前打开的文件描述符数量与最大文件描述符数量的比值。例如，"100/1024" 表示当前打开了 100 个文件，最大可打开 1024 个文件。
        fsInfo.put("fileDescriptors", String.format("%d/%d", fileSystem.getOpenFileDescriptors(), fileSystem.getMaxFileDescriptors()));
        //文件描述符的使用率。它表示当前文件描述符使用的百分比，计算公式为 (100 * openFileDescriptors / maxFileDescriptors)。通过该字段可以了解文件描述符是否已接近系统限制。
        fsInfo.put("fdUsageRate", (100d * fileSystem.getOpenFileDescriptors() / fileSystem.getMaxFileDescriptors()) + "%");
        //文件系统中的所有文件存储（即磁盘分区或文件系统挂载点）的信息列表。每个文件存储信息包含以下字段：
        List<OSFileStore> fileStores = fileSystem.getFileStores();
        for (int i = 0; i < fileStores.size(); i++) {
            Map<String, Object> fileStoreInfo = new ConcurrentHashMap<>();
            //文件存储在列表中的索引，用于标识该文件存储。
            fileStoreInfo.put("index", i);
            OSFileStore osFileStore = fileStores.get(i);
            //文件存储对象的字符串表示形式。
            fileStoreInfo.put("toString", String.valueOf(osFileStore));
            //文件存储的名称（通常是磁盘或分区的名称）。
            fileStoreInfo.put("name", osFileStore.getName());
            //文件存储的描述，通常提供了文件系统类型或设备信息。
            fileStoreInfo.put("description", osFileStore.getDescription());
            //文件存储的总空间大小，通过 FormatUtil.formatBytes 方法格式化为人类易读的字节单位（如 GB、MB 等）。
            fileStoreInfo.put("totalSpace", FormatUtil.formatBytes(osFileStore.getTotalSpace()));
            //文件存储已使用的空间大小，等于总空间减去可用空间。
            fileStoreInfo.put("usedSpace", FormatUtil.formatBytes(osFileStore.getTotalSpace() - osFileStore.getUsableSpace()));
            //文件存储的可用空间大小。
            fileStoreInfo.put("usableSpace", FormatUtil.formatBytes(osFileStore.getUsableSpace()));
            //文件存储的使用率，表示已使用空间与总空间的百分比，计算公式为 (100 * (totalSpace - usableSpace) / totalSpace)。
            fileStoreInfo.put("usageRate", 100d * (osFileStore.getTotalSpace() - osFileStore.getUsableSpace()) / osFileStore.getTotalSpace());
            //文件存储的卷标或卷名称，通常表示磁盘卷标或挂载的卷名称。
            fileStoreInfo.put("volume", osFileStore.getVolume());
            //文件存储的挂载点，表示该文件系统或分区被挂载在系统中的位置。
            fileStoreInfo.put("mount", osFileStore.getMount());
            //逻辑卷信息，如果该文件存储属于逻辑卷管理（LVM）的一部分，这个字段将提供相应的逻辑卷名称。
            fileStoreInfo.put("logicalVolume", osFileStore.getLogicalVolume());
            //文件系统的总 inode 数量。inode 是操作系统用于管理文件系统中文件和目录元数据的数据结构。
            fileStoreInfo.put("totalInodes", FormatUtil.formatValue(osFileStore.getTotalInodes(), ""));
            //文件系统中剩余的空闲 inode 数量。
            fileStoreInfo.put("freeInodes", FormatUtil.formatValue(osFileStore.getFreeInodes(), ""));
            //inode 使用率，表示已使用 inode 数量与总 inode 数量的比值，计算公式为 (100 * freeInodes / totalInodes)。
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
        //获取到的操作系统中的所有逻辑卷组（LVG）的列表。逻辑卷组是由物理卷（Physical Volume, PV）构成的存储池，它包含了一个或多个逻辑卷。
        List<LogicalVolumeGroup> logicalVolumeGroups = hal.getLogicalVolumeGroups();
        //一个包含所有逻辑卷组信息的列表。每个逻辑卷组的信息被封装在一个 Map<String, Object> 中。
        List<Map<String, Object>> lvgInfos = new ArrayList<>(logicalVolumeGroups.size());
        for (int i = 0; i < logicalVolumeGroups.size(); i++) {
            LogicalVolumeGroup lvg = logicalVolumeGroups.get(i);
            Map<String, Object> lvgInfo = new ConcurrentHashMap<>();
            //逻辑卷组在列表中的索引，用于标识该逻辑卷组的顺序。
            lvgInfo.put("index", i);
            //逻辑卷组对象的字符串表示形式。通过调用 String.valueOf(lvg) 将逻辑卷组对象转为字符串，通常会返回该对象的 toString() 方法的输出。
            lvgInfo.put("toString", String.valueOf(lvg));
            //逻辑卷组的名称。逻辑卷组通常会有一个唯一的标识名称。
            lvgInfo.put("name", lvg.getName());
            //该逻辑卷组中的所有逻辑卷（Logical Volumes, LV）的列表。逻辑卷是逻辑卷组内的一个虚拟分区，用于存储数据，用户可以将其格式化为文件系统并挂载到操作系统中。
            lvgInfo.put("logicalVolumes", lvg.getLogicalVolumes());
            //该逻辑卷组中的所有物理卷（Physical Volumes, PV）的列表。物理卷是实际的存储设备（如硬盘或硬盘分区），它们通过 LVM（逻辑卷管理）结合形成逻辑卷组。物理卷提供存储空间给逻辑卷组。
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
                partitionMap.put("diskName", hwDiskStore.getName());
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
        List<OSService> services = operatingSystem.getServices();
        List<Map<String, Object>> systemServiceList = new ArrayList<>(services.size());
        for (int i = 0; i < services.size(); i++) {
            OSService osService = services.get(i);
            Map<String, Object> osServiceMap = new ConcurrentHashMap<>();
            //当前服务在列表中的索引位置，用于标识该服务在返回结果中的位置。它是一个从 0 开始的序号。
            osServiceMap.put("index", i);
            //当前服务的字符串表示，通常是通过调用 String.valueOf(osService) 获取，通常会返回服务对象的 toString() 方法输出，描述该服务的基本信息。
            osServiceMap.put("toString", String.valueOf(osService));
            //当前服务的状态，调用 osService.getState() 获取。服务的状态是 OSService.State 枚举类型，通常包含如 RUNNING、STOPPED 等状态值。通过 osService.getState().name() 获取其名称（字符串形式）。
            osServiceMap.put("state", osService.getState().name());
            //当前服务的进程ID，表示该服务正在运行的进程标识符。通过 osService.getProcessID() 获取服务对应进程的ID。
            osServiceMap.put("pid", osService.getProcessID());
            //当前服务的名称，通过 osService.getName() 获取服务的名称。这个名称通常是服务的标识符或注册名称。
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
        Map<String, Object> sensorInfo = new ConcurrentHashMap<>();
        Sensors sensors = hal.getSensors();
        //将 Sensors 对象转换为字符串，通过调用 String.valueOf(sensors) 获取。通常 toString() 方法返回的是该对象的简要描述或一些关键信息，可以用来了解该对象的基本情况。
        sensorInfo.put("toString", String.valueOf(sensors));
        //该字段直接存储了 Sensors 对象实例本身。它可以用来获取更详细的传感器信息或访问具体的传感器数据。它没有经过额外处理或格式化。
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
        // 当前操作系统的名称，通常包括操作系统的类型、版本等。
        osInfo.put("osName", String.valueOf(operatingSystem));
        //系统启动时间。
        osInfo.put("booted", Instant.ofEpochSecond(operatingSystem.getSystemBootTime()));
        //当前系统的所有会话信息
        osInfo.put("sessionList", operatingSystem.getSessions());
        //当前计算机的基本信息
        ComputerSystem computerSystem = hal.getComputerSystem();
        osInfo.put("computerSystem", String.valueOf(computerSystem));
        //计算机的固件信息。
        osInfo.put("firmware", computerSystem.getFirmware());
        //计算机的主板信息
        osInfo.put("baseboard", computerSystem.getBaseboard());
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
        //CPU 信息的字符串表示。
        cpuInfo.put("toString", String.valueOf(processor));
        //获取CPU实例对象，CentralProcessor 是一个表示 CPU 的类，它提供了有关 CPU 的更多详细信息，例如处理器的核心数量、型号、频率等。
        cpuInfo.put("instance", processor);
        return cpuInfo;
    }

    /**
     * 读取显示器信息
     *
     * @return {@link List}
     */
    public static List<Map<String, Object>> getDisplayInformation() {
        //显示器列表，包含系统中所有连接的显示器信息。每个 Display 对象代表一个显示器。
        List<Display> displays = hal.getDisplays();
        //该列表用于存储每个显示器的信息。每个显示器的信息通过 Map<String, Object> 表示，键值对形式存储各种与该显示器相关的属性。
        List<Map<String, Object>> displayInfos = new ArrayList<>(displays.size());
        for (int i = 0; i < displays.size(); i++) {
            //每个显示器的详细信息，通过键值对的形式存储在 Map 中。键是属性名，值是对应的属性值。
            Map<String, Object> displayInfo = new ConcurrentHashMap<>();
            //显示器在列表中的索引，表示该显示器在系统中是第几个显示器。
            displayInfo.put("index", String.valueOf(i));
            final Display display = displays.get(i);
            //从 Display 对象获取的 EDID 数据（扩展显示识别数据）。EDID 是显示器与计算机通信时传输的标准数据格式，用来描述显示器的基本信息和特性。
            byte[] edidBytes = display.getEdid();
            //显示器制造商的 ID，这是制造商的唯一标识符。
            displayInfo.put("manufacturerId", EdidUtil.getManufacturerID(edidBytes));
            //显示器的产品 ID，EDID 中还包含显示器的产品标识符。
            displayInfo.put("productId", EdidUtil.getProductID(edidBytes));
            //判断显示器是否是数字显示器，如果是数字显示器，返回 "Digital"；否则，返回 "Analog"。
            displayInfo.put("isDigital", EdidUtil.isDigital(edidBytes) ? "Digital" : "Analog");
            //显示器的序列号，显示器的唯一序列号。
            displayInfo.put("serialNo", EdidUtil.getSerialNo(edidBytes));
            //显示器的制造日期， 获取的日期信息格式为："月/年"。
            displayInfo.put("manufacturerDate", (EdidUtil.getWeek(edidBytes) * 12 / 52 + 1) + '/' + EdidUtil.getYear(edidBytes));
            //显示器 EDID 版本号
            displayInfo.put("version", EdidUtil.getVersion(edidBytes));
            //显示器的高度和宽度，以厘米为单位
            displayInfo.put("cmHeight", EdidUtil.getHcm(edidBytes));
            displayInfo.put("cmWidth", EdidUtil.getVcm(edidBytes));
            //显示器的高度和宽度，以英寸为单位.
            displayInfo.put("inHeight", EdidUtil.getHcm(edidBytes) / 2.54);
            displayInfo.put("inWidth", EdidUtil.getVcm(edidBytes) / 2.54);
            //显示器的 toString() 方法返回的字符串，通常是显示器的简要描述。
            displayInfo.put("toString", String.valueOf(display));
            byte[][] descriptorBytes = EdidUtil.getDescriptors(edidBytes);
            for (byte[] bytes : descriptorBytes) {
                switch (EdidUtil.getDescriptorType(bytes)) {
                    //0xff: 序列号。
                    case 0xff:
                        displayInfo.put("serialNumber", EdidUtil.getDescriptorText(bytes));
                        break;
                    //0xfe: 未指定文本（通常是默认或空白描述）。
                    case 0xfe:
                        displayInfo.put("unspecifiedText", EdidUtil.getDescriptorText(bytes));
                        break;
                    //0xfd: 范围限制（例如显示器支持的分辨率范围）。
                    case 0xfd:
                        displayInfo.put("rangeLimits", EdidUtil.getDescriptorRangeLimits(bytes));
                        break;
                    //0xfc: 显示器名称。
                    case 0xfc:
                        displayInfo.put("monitorName", EdidUtil.getDescriptorText(bytes));
                        break;
                    //白点数据（显示器色彩校准信息）。
                    case 0xfb:
                        displayInfo.put("whitePointData", ParseUtil.byteArrayToHexString(bytes));
                        break;
                    //标准时序 ID（显示器的标准显示时序）
                    case 0xfa:
                        displayInfo.put("standardTimingId", ParseUtil.byteArrayToHexString(bytes));
                        break;
                    default:
                        //0x00 - 0x0f: 制造商特定的数据，通常是显示器的扩展功能或厂商专有的信息
                        if (EdidUtil.getDescriptorType(bytes) <= 0x0f && EdidUtil.getDescriptorType(bytes) >= 0x00) {
                            displayInfo.put("manufacturerData", ParseUtil.byteArrayToHexString(bytes));
                        } else {//表示显示器的首选时序。
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
        // JVM 中总的内存量。表示 JVM 分配给程序的总内存大小。该值包括已使用和未使用的内存。
        jvmMemory.put("total", FormatUtil.formatBytes(totalMem));
        //JVM 最大可分配的内存。表示 JVM 可以使用的最大内存量。这个值由 JVM 启动时的配置参数（如 -Xmx）决定。
        jvmMemory.put("max", FormatUtil.formatBytes(maxMem));
        // JVM 中当前未被使用的内存。表示当前空闲的内存空间。
        jvmMemory.put("free", FormatUtil.formatBytes(freeMem));
        //JVM 中已使用的内存。通过计算 totalMem - freeMem 来获取，表示当前已经被 JVM 使用的内存量。
        jvmMemory.put("used", FormatUtil.formatBytes(usedMem));
        // JVM 内存的使用率。通过 (100D * usedMem / totalMem) 计算并格式化为百分比，表示当前已使用的内存占总内存的百分比。
        jvmMemory.put("usageRate", String.format("%.2f", 100D * usedMem / totalMem) + "%");
        //JVM 内存的空闲率。通过 (100D * freeMem / totalMem) 计算并格式化为百分比，表示当前空闲的内存占总内存的百分比。
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
