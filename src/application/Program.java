package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Digite o caminho do arquivo: ");
		String path = sc.nextLine();

		try (BufferedReader bf = new BufferedReader(new FileReader(path))) {

			List<Employee> list = new ArrayList<>();
			String line = bf.readLine();

			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = bf.readLine();
			}

			System.out.print("Digite o salário que deseja saber : ");
			double salary = sc.nextDouble();

			List<String> emails = list.stream()
					.filter(x -> x.getSalary() > salary)
					.map(x -> x.getEmail())
					.sorted()
					.collect(Collectors.toList());

			System.out.println("Email das pessoas que possuem salário maior que o fornecido: " 
			+ String.format("%.2f", salary));
			emails.forEach(System.out::println);
			
			double soma = list.stream()
					.filter(x -> x.getName().charAt(0) == 'M')
					.map(x -> x.getSalary())
					.reduce(0.0, (x,y) -> x+ y);
			
			System.out.println("Soma do salário das Pessoas cujo nome inicia com a letra M: "
					+ String.format("%.2f", soma));
			
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}

	}

}
