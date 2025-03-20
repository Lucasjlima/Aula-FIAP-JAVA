
package ccr.tdspa.infrastructure;

import ccr.tdspa.entities.Evento;
import ccr.tdspa.entities.Funcionario;
import ccr.tdspa.repositories.FuncionarioRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AplicationTestConfig {

    public static Logger logger = LogManager.getLogger(AplicationTestConfig.class);

    private static final long MIN_MEMORY_REQUIRED = 200 * 1024 * 1024; // 200 MB
    private static final double MAX_CPU_LOAD = 0.9; // 90%
    private static final long MIN_DISK_SPACE = 500 * 1024 * 1024; // 500 MB
    private static final String TEST_HOST = "google.com";
    private static final String DB_URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String DB_USER = "rm561160";
    private static final String DB_PASSWORD = "260406";

    public static void testAplication(String[] args) {
        if (checkMemory() && checkCpuLoad() && checkDiskSpace() && checkNetworkConnectivity() && checkImportantFunctions() && checkDatabaseConnection()) {
            logger.info("Todos os testes passaram. Iniciando a aplicação...");
        } else {
            logger.error("Um ou mais testes falharam. Por favor resolva os erros e tente novamente.");
        }
    }

    private static boolean checkMemory() {
        Runtime runtime = Runtime.getRuntime();
        long freeMemory = runtime.freeMemory();
        long totalMemory = runtime.totalMemory();
        long maxMemory = runtime.maxMemory();
        long availableMemory = freeMemory + (maxMemory - totalMemory);

        logger.info("Memória disponível: " + availableMemory / (1024 * 1024) + " MB");

        return availableMemory >= MIN_MEMORY_REQUIRED;
    }

    private static boolean checkCpuLoad() {
        com.sun.management.OperatingSystemMXBean osBean =
                (com.sun.management.OperatingSystemMXBean) java.lang.management.ManagementFactory.getOperatingSystemMXBean();
        double cpuLoad = osBean.getSystemCpuLoad();

        logger.info("CPU load: " + (cpuLoad * 100) + " %");

        return cpuLoad < MAX_CPU_LOAD;
    }

    private static boolean checkDiskSpace() {
        try {
            for (FileStore store : FileSystems.getDefault().getFileStores()) {
                long availableSpace = store.getUsableSpace();
                logger.info("Espaço disponível no disco: " + availableSpace / (1024 * 1024) + " MB");
                if (availableSpace < MIN_DISK_SPACE) {
                    return false;
                }
            }
        } catch (IOException e) {
            logger.error("Erro checando espaço em disco: " + e.getMessage());
            return false;
        }
        return true;
    }

    private static boolean checkNetworkConnectivity() {
        try {
            InetAddress address = InetAddress.getByName(TEST_HOST);
            boolean reachable = address.isReachable(5000); // 5 seconds timeout
            logger.info("Conexão de internet para " + TEST_HOST + ": " + (reachable ? "reachable" : "not reachable"));
            return reachable;
        } catch (IOException e) {
            logger.error("Erro checando conexão com internet: " + e.getMessage());
            return false;
        }
    }

    private static boolean checkImportantFunctions() {
        try {
            Method method = FuncionarioRepository.class.getMethod("menu", FuncionarioRepository.class);
            if (method != null) {
                logger.info("Função importante 'menuFunc' está disponível.");
                return true;
            }
        } catch (NoSuchMethodException e) {
            logger.error("Função importante 'menu' não encontrada: " + e.getMessage());
        }
        return false;
    }

    private static boolean checkDatabaseConnection() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            if (connection != null) {
                logger.info("Conexão com o banco de dados estabelecida com sucesso.");
                return true;
            }
        } catch (SQLException e) {
            logger.error("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
        return false;
    }
}