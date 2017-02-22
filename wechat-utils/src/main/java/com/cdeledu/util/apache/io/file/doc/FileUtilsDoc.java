package com.cdeledu.util.apache.io.file.doc;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.Checksum;

import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.IOFileFilter;

/**
 * 
 * @ClassName: FileUtilsDoc
 * @Description: FileUtils 方法说明文档
 * @author: 独泪了无痕
 * @date: 2015年8月29日 下午2:24:57
 * @version: V1.0
 * @history:
 */
public interface FileUtilsDoc {
	/** 字节转换成直观带单位的值（包括单位GB，MB，KB或字节） */
	public String byteCountToDisplaySize(BigInteger size);

	/** 字节转换成直观带单位的值（包括单位GB，MB，KB或字节） */
	public String byteCountToDisplaySize(long size);

	/** 计算一个文件使用指定的校验对象的校验 */
	public Checksum checksum(File file, Checksum checksum);

	/** 计算使用CRC32校验程序文件的校验和 */
	public long checksumCRC32(File file);

	/** 清除一个目录而不删除它 */
	public void cleanDirectory(File directory);

	/** 判断两个文件是否相等 */
	public boolean contentEquals(File file1, File file2);

	/** 判断两个文件是否相等 */
	public boolean contentEqualsIgnoreEOL(File file1, File file2,
			String charsetName);

	/** 把相应的文件集合转换成文件数组 */
	public File[] convertFileCollectionToFileArray(Collection<File> files);

	/** 拷贝整个目录到新的位置，并且保存最近修改时间 */
	public void copyDirectory(File srcDir, File destDir);

	/** 拷贝整个目录到新的位置，并且设置是否保存最近修改时间 */
	public void copyDirectory(File srcDir, File destDir,
			boolean preserveFileDate);

	/** 拷贝过滤后的目录到指定的位置，并且保存最近修改时间 */
	public void copyDirectory(File srcDir, File destDir, FileFilter filter);

	/** 拷贝过滤后的目录到指定的位置，并且设置是否保存最近修改时间 */
	public void copyDirectory(File srcDir, File destDir, FileFilter filter,
			boolean preserveFileDate);

	/** 将一个目录拷贝到另一目录中，并且保存最近更新时间 */
	public void copyDirectoryToDirectory(File srcDir, File destDir);

	/** 拷贝文件到新的文件中并且保存最近修改时间 */
	public void copyFile(File srcFile, File destFile);

	/** 拷贝文件到新的文件中并且设置是否保存最近修改时间 */
	public void copyFile(File srcFile, File destFile, boolean preserveFileDate);

	public long copyFile(File input, OutputStream output);

	/** 拷贝一个文件到指定的目录文件 */
	public void copyFileToDirectory(File srcFile, File destDir);

	/** 拷贝一个文件到指定的目录文件并且设置是否更新文件的最近修改时间 */
	public void copyFileToDirectory(File srcFile, File destDir,
			boolean preserveFileDate);

	/** 拷贝一个字节流到一个文件中，如果这个文件不存在则新创建一个，存在的话将被重写进内容 */
	public void copyInputStreamToFile(InputStream source, File destination);

	/** 根据一个Url拷贝字节到一个文件中 */
	public void copyURLToFile(URL source, File destination);

	/** 根据一个Url拷贝字节到一个文件中，并且可以设置连接的超时时间和读取的超时时间 */
	public void copyURLToFile(URL source, File destination,
			int connectionTimeout, int readTimeout);

	/** 递归的删除一个目录 */
	public void deleteDirectory(File directory);

	/** 安静模式删除目录，操作过程中会抛出异常 */
	public boolean deleteQuietly(File file);

	public boolean directoryContains(File directory, File child);

	/** 删除一个文件，如果是目录则递归删除 */
	public void forceDelete(File file);

	/** 当虚拟机退出关闭时删除文件 */
	public void forceDeleteOnExit(File file);

	/** 创建一个目录除了不存在的父目录其他所必须的都可以创建 */
	public void forceMkdir(File directory);

	public File getFile(File directory, String... names);

	public File getFile(String... names);

	/** 获取代表系统临时目录的文件 */
	public File getTempDirectory();

	/** 获取系统的临时目录路径： */
	public String getTempDirectoryPath();

	/** 获取代表用户主目录的文件： */
	public File getUserDirectory();

	/** 获取用户的主目录路径： */
	public String getUserDirectoryPath();

	/** 检测指定文件的最后修改时间是否在指定日期之前 */
	public boolean isFileNewer(File file, Date date);

	/** 测试指定文件的最后修改日期是否比reference的文件新 */
	public boolean isFileNewer(File file, File reference);

	/** 检测指定文件的最后修改时间（毫秒）是否在指定日期之前 */
	public boolean isFileNewer(File file, long timeMillis);

	/** 检测指定文件的最后修改时间是否在指定日期之后 */
	public boolean isFileOlder(File file, Date date);

	/** 检测指定文件的最后修改日期是否比reference文件的晚 */
	public boolean isFileOlder(File file, File reference);

	/** 检测指定文件的最后修改时间（毫秒）是否在指定日期之后 */
	public boolean isFileOlder(File file, long timeMillis);

	/** 确定指定的文件是否是一个符号链接，而不是实际的文件 */
	public boolean isSymlink(File file);

	/** 根据一个IOFileFilter过滤规则获取一个目录下的文件集合的Iterator迭代器 */
	 public Iterator<File> iterateFiles(File directory, IOFileFilter
	 fileFilter, IOFileFilter dirFilter);
	/** 根据一个IOFileFilter过滤规则获取一个目录下的文件集合的Iterator迭代器 */
	public Iterator<File> iterateFiles(File directory, String[] extensions,
			boolean recursive);

	/** 根据一个IOFileFilter过滤规则获取一个目录下的文件集合的Iterator迭代器 */
	 public Iterator<File> iterateFilesAndDirs(File directory, IOFileFilter
	 fileFilter, IOFileFilter dirFilter);
	/** 根据对应编码返回对应文件内容的行迭代器 */
	 public LineIterator lineIterator(File file);
	 public LineIterator lineIterator(File file, String encoding);
	/** 根据一个IOFileFilter过滤规则获取一个目录下的文件集合 */
	 public Collection<File> listFiles(File directory, IOFileFilter
	 fileFilter, IOFileFilter dirFilter);
	/** 根据一个IOFileFilter过滤规则获取一个目录下的文件集合 */
	public Collection<File> listFiles(File directory, String[] extensions,
			boolean recursive);

	/** 根据一个IOFileFilter过滤规则获取一个目录下的文件集合 */
	 public Collection<File> listFilesAndDirs(File directory, IOFileFilter
	 fileFilter, IOFileFilter dirFilter);
	/** 移动目录到新的目录并且删除老的目录 */
	public void moveDirectory(File srcDir, File destDir);

	/** 把一个目录移动到另一个目录中去 */
	public void moveDirectoryToDirectory(File src, File destDir,
			boolean createDestDir);

	/** 复制文件到对应的文件中去 */
	public void moveFile(File srcFile, File destFile);

	/** 复制文件到对应的文件中去，可设置当目标文件不存在时是否创建新的文件 */
	public void moveFileToDirectory(File srcFile, File destDir,
			boolean createDestDir);

	/** 移动文件或者目录到新的路径下，并且设置在目标路径不存在的情况下是否创建 */
	public void moveToDirectory(File src, File destDir, boolean createDestDir);

	/** 根据指定的文件获取一个新的文件输入流： */
	public FileInputStream openInputStream(File file);

	/** 根据指定的文件获取一个新的文件输出流 */
	public FileOutputStream openOutputStream(File file);

	/** 根据指定的文件获取一个新的文件输出流 */
	public FileOutputStream openOutputStream(File file, boolean append);

	/** 把一个文件转换成字节数组返回 */
	public byte[] readFileToByteArray(File file);

	/** 读取文件的内容到虚拟机的默认编码字符串 */
	public String readFileToString(File file);

	/** 把一个文件的内容读取到一个对应编码的字符串中去 */
	public String readFileToString(File file, Charset encoding);

	public String readFileToString(File file, String encoding);

	/** 把文件中的内容逐行的拷贝到一个虚拟机默认编码的 */
	public List<String> readLines(File file);

	public List<String> readLines(File file, Charset encoding);

	/** 把文件中的内容逐行的拷贝到一个对应编码的 */
	public List<String> readLines(File file, String encoding);

	/** 获取文件或者目录的大小 */
	public long sizeOf(File file);

	public BigInteger sizeOfAsBigInteger(File file);

	/** 获取目录的大小 */
	public long sizeOfDirectory(File directory);

	public BigInteger sizeOfDirectoryAsBigInteger(File directory);

	/** 根据一个Url来创建一个文件 */
	public File toFile(URL url);

	/** 将一个URL数组转化成一个文件数组 */
	public File[] toFiles(URL[] urls);

	/** 创建一个空文件，若文件应经存在则只更改文件的最近修改时间： */
	public void touch(File file);

	/** 将一个文件数组转化成一个URL数组 */
	public URL[] toURLs(File[] files);

	/** 等待NFS来传播一个文件的创建，实施超时 */
	public boolean waitFor(File file, int seconds);

	/** 根据虚拟机默认的编码把CharSequence写入到文件中 */
	public void write(File file, CharSequence data);

	public void write(File file, CharSequence data, boolean append);

	public void write(File file, CharSequence data, Charset encoding);

	public void write(File file, CharSequence data, Charset encoding,
			boolean append);

	/** 根据对应的编码把CharSequence写入到文件中 */
	public void write(File file, CharSequence data, String encoding);

	public void write(File file, CharSequence data, String encoding,
			boolean append);

	/** 把一个字节数组写入到指定的文件中 */
	public void writeByteArrayToFile(File file, byte[] data);

	public void writeByteArrayToFile(File file, byte[] data, boolean append);

	/** 把集合中的内容根据虚拟机默认编码逐项插入到文件中 */
	public void writeLines(File file, Collection<?> lines);

	public void writeLines(File file, Collection<?> lines, boolean append);

	/** 把集合中的内容根据对应行编码逐项插入到文件中 */
	public void writeLines(File file, Collection<?> lines, String lineEnding);

	public void writeLines(File file, Collection<?> lines, String lineEnding,
			boolean append);

	/** 把集合中的内容根据对应编码逐项插入到文件中 */
	public void writeLines(File file, String encoding, Collection<?> lines);

	public void writeLines(File file, String encoding, Collection<?> lines,
			boolean append);

	/** 把集合中的内容根据对应字符编码和行编码逐项插入到文件中 */
	public void writeLines(File file, String encoding, Collection<?> lines,
			String lineEnding);

	public void writeLines(File file, String encoding, Collection<?> lines,
			String lineEnding, boolean append);

	/** 根据虚拟机默认编码把字符串写进对应的文件中 */
	public void writeStringToFile(File file, String data);

	public void writeStringToFile(File file, String data, boolean append);

	public void writeStringToFile(File file, String data, Charset encoding);

	public void writeStringToFile(File file, String data, Charset encoding,
			boolean append);

	/** 根据对应编码把字符串写进对应的文件中 */
	public void writeStringToFile(File file, String data, String encoding);

	public void writeStringToFile(File file, String data, String encoding,
			boolean append);
}
