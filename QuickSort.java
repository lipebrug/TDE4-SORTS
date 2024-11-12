import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuickSort {

    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int[] readCSV(String filePath) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
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
        QuickSort quickSort = new QuickSort();

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
            for (int num : array) {
                System.out.print(num + " ");
            }
            System.out.println();

            long startTime = System.nanoTime();
            quickSort.sort(array);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;

            System.out.println("Array ordenado:");
            for (int num : array) {
                System.out.print(num + " ");
            }
            System.out.println("\nTempo de execução (em nanossegundos): " + duration);
            System.out.println("---------------------------------------------------");
        }
    }
}
