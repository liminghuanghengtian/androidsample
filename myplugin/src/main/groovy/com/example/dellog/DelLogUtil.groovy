package com.example.dellog

class DelLogUtil {

    public static final String head = 'Log.'
    public static final String fully_qualified_head = "android.util.Log."
    public static final String tail_end = ');'
    public static final String suffix = '.java'
    public static final String charset = "utf-8"

    static void delLog(File rootFile) {
        if (rootFile == null) {
            return
        }

        if (rootFile.isDirectory()) {
            rootFile.eachFile { File file ->
                if (file.isFile()) {
                    if (file.canRead() && file.name.endsWith(suffix)) {
                        println "----- file: " + file.getAbsolutePath() + " -----"
                        delFileLog(file)
                    }
                } else if (file.isDirectory()) {
                    println "dir: " + file.getAbsolutePath()
                    delLog(file)
                }
            }
        }
    }

    /**
     * 删除java文件内的Log语句
     * @param file
     */
    private static void delFileLog(File file) {
        // Log日志打印语句是否换行，等于0表示单行。等于1表示多行语句
        def endFlag = 0
        File tmpFile = File.createTempFile(file.getAbsolutePath(), ".tmp")
        def printWriter = tmpFile.newPrintWriter(charset)
        def reader = file.newReader(charset)
        def tmpLogLine
        String line
        while ((line = reader.readLine()) != null) {
            if (line != null) {
                // 去除前后的空格，才能正确判断head和tail
                tmpLogLine = line.trim()
                if (tmpLogLine.startsWith(head) || tmpLogLine.startsWith(fully_qualified_head) || endFlag == 1) {
                    if (tmpLogLine.endsWith(tail_end)) {
                        endFlag = 0
                        // printWriter.write("\n")
                        printWriter.write(getLeadingWhiteSpace(line) + "// Log.i(\"dellog\", \"原先的log日志打印语句已被删除\");\n")
                    } else {
                        endFlag = 1
                    }
                } else {
                    printWriter.write(line + "\n")
                }
            }
        }

        reader.close()

        printWriter.flush()
        printWriter.close()

        file.delete()
        tmpFile.renameTo(file.getAbsolutePath())
    }

    /**
     * 获取语句前段的空格
     * @param line
     * @return
     */
    private static String getLeadingWhiteSpace(String line) {
        int len = line.length()
        int st = 0

        while ((st < len) && (line.charAt(st) <= ' ')) {
            st++
        }
        return (st > 0) ? line.substring(0, st) : ''
    }
}