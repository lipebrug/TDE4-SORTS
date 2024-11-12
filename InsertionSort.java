import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InsertionSort {

    public void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static int[] readCSV(String filePath) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] values = line.split(",");
                for (String value : values) {
                    try {
                        numbers.add(Integer.parseInt(value.trim()));
                    } catch (NumberFormatException e) {
                        System.out.println("Valor ignorado (não numérico): " + value);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return numbers.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        InsertionSort sorter = new InsertionSort();

        String[] filePaths = {
                "src/conjuntosDeDados/aleatorio_100.csv",
                "src/conjuntosDeDados/aleatorio_1000.csv",
                "src/conjuntosDeDados/aleatorio_10000.csv",
                "src/conjuntosDeDados/crescente_100.csv",
                "src/conjuntosDeDados/crescente_1000.csv",
                "src/conjuntosDeDados/crescente_10000.csv",
                "src/conjuntosDeDados/decrescente_100.csv",
                "src/conjuntosDeDados/decrescente_1000.csv",
                "src/conjuntosDeDados/decrescente_10000.csv"
        };

        for (String filePath : filePaths) {
            System.out.println("Lendo arquivo: " + filePath);
            int[] array = readCSV(filePath);

            System.out.println("Array original:");
            sorter.printArray(array);

            long startTime = System.nanoTime();
            sorter.insertionSort(array);
            long endTime = System.nanoTime();

            long duration = endTime - startTime;

            System.out.println("Array ordenado:");
            sorter.printArray(array);
            System.out.println("Tempo de execução (em nanossegundos): " + duration);
            System.out.println("---------------------------------------------------");
        }
    }
}
