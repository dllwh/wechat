package com.cdeledu.util.shell.common;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

/**
 * @类描述: Script执行错误码
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年5月27日 上午8:41:22
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ScriptOpCode {
	static Map<Integer, String> resultMap = Maps.newHashMap();

	static {
		resultMap.put(0, "OK");
		resultMap.put(1, "Operation not permitted");
		resultMap.put(2, "No such file or directory");
		resultMap.put(3, "No such process");
		resultMap.put(4, "Interrupted system call");
		resultMap.put(5, "Input/output error");
		resultMap.put(6, "No such device or address");
		resultMap.put(7, "Argument list too long");
		resultMap.put(8, "Exec format error");
		resultMap.put(9, "Bad file descriptor");
		resultMap.put(10, "No child processes");
		resultMap.put(11, "Resource temporarily unavailable");
		resultMap.put(12, "Cannot allocate memory");
		resultMap.put(13, "Permission denied");
		resultMap.put(14, "Bad address");
		resultMap.put(15, "Block device required");
		resultMap.put(16, "Device or resource busy");
		resultMap.put(17, "File exists");
		resultMap.put(18, "Invalid cross-device link");
		resultMap.put(19, "No such device");
		resultMap.put(20, "Not a directory");
		resultMap.put(21, "Is a directory");
		resultMap.put(22, "Invalid argument");
		resultMap.put(23, "Too many open files in system");
		resultMap.put(24, "Too many open files");
		resultMap.put(25, "Inappropriate ioctl for device");
		resultMap.put(26, "Text file busy");
		resultMap.put(27, "File too large");
		resultMap.put(28, "No space left on device");
		resultMap.put(29, "Illegal seek");
		resultMap.put(30, "Read-only file system");
		resultMap.put(31, "Too many links");
		resultMap.put(32, "Broken pipe");
		resultMap.put(33, "Numerical argument out of domain");
		resultMap.put(34, "Numerical result out of range");
		resultMap.put(35, "Resource deadlock avoided");
		resultMap.put(36, "File name too long");
		resultMap.put(37, "No locks available");
		resultMap.put(38, "Function not implemented");
		resultMap.put(39, "Directory not empty");
		resultMap.put(40, "Too many levels of symbolic links");
		resultMap.put(42, "No message of desired type");
		resultMap.put(43, "Identifier removed");
		resultMap.put(44, "Channel number out of range");
		resultMap.put(45, "Level 2 not synchronized");
		resultMap.put(46, "Level 3 halted");
		resultMap.put(47, "Level 3 reset");
		resultMap.put(48, "Link number out of range");
		resultMap.put(49, "Protocol driver not attached");
		resultMap.put(50, "No CSI structure available");
		resultMap.put(51, "Level 2 halted");
		resultMap.put(52, "Invalid exchange");
		resultMap.put(53, "Invalid request descriptor");
		resultMap.put(54, "Exchange full");
		resultMap.put(55, "No anode");
		resultMap.put(56, "Invalid request code");
		resultMap.put(57, "Invalid slot");
		resultMap.put(59, "Bad font file format");
		resultMap.put(60, "Device not a stream");
		resultMap.put(61, "No data available");
		resultMap.put(62, "Timer expired");
		resultMap.put(63, "Out of streams resources");
		resultMap.put(64, "Machine is not on the network");
		resultMap.put(65, "Package not installed");
		resultMap.put(66, "Object is remote");
		resultMap.put(67, "Link has been severed");
		resultMap.put(68, "Advertise error");
		resultMap.put(69, "Srmount error");
		resultMap.put(70, "Communication error on send");
		resultMap.put(71, "Protocol error");
		resultMap.put(72, "Multihop attempted");
		resultMap.put(73, "RFS specific error");
		resultMap.put(74, "Bad message");
		resultMap.put(75, "Value too large for defined data type");
		resultMap.put(76, "Name not unique on network");
		resultMap.put(77, "File descriptor in bad state");
		resultMap.put(78, "Remote address changed");
		resultMap.put(79, "Can not access a needed shared library");
		resultMap.put(80, "Accessing a corrupted shared library");
		resultMap.put(81, ".lib section in a.out corrupted");
		resultMap.put(82, "Attempting to link in too many shared libraries");
		resultMap.put(83, "Cannot exec a shared library directly");
		resultMap.put(84, "Invalid or incomplete multibyte or wide character");
		resultMap.put(85, "Interrupted system call should be restarted");
		resultMap.put(86, "Streams pipe error");
		resultMap.put(87, "Too many users");
		resultMap.put(88, "Socket operation on non-socket");
		resultMap.put(89, "Destination address required");
		resultMap.put(90, "Message too long");
		resultMap.put(91, "Protocol wrong type for socket");
		resultMap.put(92, "Protocol not available");
		resultMap.put(93, "Protocol not supported");
		resultMap.put(94, "Socket type not supported");
		resultMap.put(95, "Operation not supported");
		resultMap.put(96, "Protocol family not supported");
		resultMap.put(97, "Address family not supported by protocol");
		resultMap.put(98, "Address already in use");
		resultMap.put(99, "Cannot assign requested address");
		resultMap.put(100, "Network is down");
		resultMap.put(101, "Network is unreachable");
		resultMap.put(102, "Network dropped connection on reset");
		resultMap.put(103, "Software caused connection abort");
		resultMap.put(104, "Connection reset by peer");
		resultMap.put(105, "No buffer space available");
		resultMap.put(106, "Transport endpoint is already connected");
		resultMap.put(107, "Transport endpoint is not connected");
		resultMap.put(108, "Cannot send after transport endpoint shutdown");
		resultMap.put(109, "Too many references: cannot splice");
		resultMap.put(110, "Connection timed out");
		resultMap.put(111, "Connection refused");
		resultMap.put(112, "Host is down");
		resultMap.put(113, "No route to host");
		resultMap.put(114, "Operation already in progress");
		resultMap.put(115, "Operation now in progress");
		resultMap.put(116, "Stale NFS file handle");
		resultMap.put(117, "Structure needs cleaning");
		resultMap.put(118, "Not a XENIX named type file");
		resultMap.put(119, "No XENIX semaphores available");
		resultMap.put(120, "Is a named type file");
		resultMap.put(121, "Remote I/O error");
		resultMap.put(122, "Disk quota exceeded");
		resultMap.put(123, "No medium found");
		resultMap.put(124, "Wrong medium type");
		resultMap.put(125, "Operation canceled");
		resultMap.put(126, "Required key not available");
		resultMap.put(127, "Key has expired");
		resultMap.put(128, "Key has been revoked");
		resultMap.put(129, "Key was rejected by service");
		resultMap.put(130, "Owner died");
		resultMap.put(131, "State not recoverable");
		resultMap.put(132, "Old database file");
		resultMap.put(133, "No record read before update");
		resultMap.put(134, "Record was already deleted (or record file crashed)");
		resultMap.put(135, "No more room in record file");
		resultMap.put(136, "No more room in index file");
		resultMap.put(137, "No more records (read after end of file)");
		resultMap.put(138, "Unsupported extension used for table");
		resultMap.put(139, "Too big row");
		resultMap.put(140, "Wrong create options");
		resultMap.put(141, "Duplicate unique key or constraint on write or update");
		resultMap.put(142, "Unknown character set used");
		resultMap.put(143, "Conflicting table definitions in sub-tables of MERGE table");
		resultMap.put(144, "Table is crashed and last repair failed");
		resultMap.put(145, "Table was marked as crashed and should be repaired");
		resultMap.put(146, "Lock timed out; Retry transaction");
		resultMap.put(147, "Lock table is full;Restart program with a larger locktable");
		resultMap.put(148, "Updates are not allowed under a read only transactions");
		resultMap.put(149, "Lock deadlock; Retry transaction");
		resultMap.put(150, "Foreign key constraint is incorrectly formed");
		resultMap.put(151, "Cannot add a child row");
		resultMap.put(152, "Cannot delete a parent row");
	}

	public static String getErrorMsg(Integer errorCode) {
		String errorMsg = resultMap.get(errorCode);
		if (StringUtils.isBlank(errorMsg)) {
			errorMsg = "unknown error";
		}
		return errorMsg;
	}

	public static void main(String[] args) {
		System.out.println(getErrorMsg(123));
	}
}
