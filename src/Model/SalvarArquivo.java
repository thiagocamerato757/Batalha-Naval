package Model;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalvarArquivo {
	private File selectedFile;
	private String namePlayer1;
	private String namePlayer2;
	private List<Navio> ships1 = new ArrayList<>();
	private List<Navio> ships2 = new ArrayList<>();
	private Map<Point, Color> shots1 = new HashMap<>();
	private Map<Point, Color> shots2 = new HashMap<>();
	private int navios_restantes1;
	private int navios_restantes2;
	private int max_tiros;
	private boolean vezJogador;
	
	public void setMaxTiros(int max_tiros) {
		this.max_tiros = max_tiros;
	}
	
	public void setVezJogador(boolean vezJogador) {
		this.vezJogador = vezJogador;
	}
	
	public void setSelectedFile(File selectedFile) {
		this.selectedFile = selectedFile;
	}

	public void setNamePlayer1(String namePlayer1) {
		this.namePlayer1 = namePlayer1;
	}
	
	public void setNavios_restantes1(int navios_restantes1) {
		this.navios_restantes1 = navios_restantes1;
	}

	public void setNavios_restantes2(int navios_restantes2) {
		this.navios_restantes2 = navios_restantes2;
	}

	public void setNamePlayer2(String namePlayer2) {
		this.namePlayer2 = namePlayer2;
	}

	public void setShips1(List<Navio> ships1) {
		this.ships1 = ships1;
	}

	public void setShips2(List<Navio> ships2) {
		this.ships2 = ships2;
	}

	public void setShots1(Map<Point, Color> shots1) {
		this.shots1 = shots1;
	}

	public void setShots2(Map<Point, Color> shots2) {
		this.shots2 = shots2;
	}

	public void writeFile(File givenFile) throws IOException {
		PrintWriter file = new PrintWriter(givenFile);
		
		if (vezJogador) {
			file.println("1");
		}
		else {
			file.println("2");
		}
		// Escreve os dados do jogador 1
		file.println(namePlayer1);
		file.println(ships1.size());
		for (Navio ship : ships1) {
			file.print(ship.getTamanho());
			for (Point coord : ship.getCoordenadas()) {
				file.print(" " + coord.x + "," + coord.y);
			}
			file.println();
		}
		file.println(navios_restantes1);
		file.println(shots1.size());
		for (Map.Entry<Point, Color> shot : shots1.entrySet()) {
			Point point = shot.getKey();
			file.println(point.x + "," + point.y + " " + shot.getValue().getRGB());
		}

		// Escreve os dados do jogador 2
		file.println(namePlayer2);
		file.println(ships2.size());
		for (Navio ship : ships2) {
			file.print(ship.getTamanho());
			for (Point coord : ship.getCoordenadas()) {
				file.print(" " + coord.x + "," + coord.y);
			}
			file.println();
		}
		file.println(navios_restantes2);
		file.println(shots2.size());
		for (Map.Entry<Point, Color> shot : shots2.entrySet()) {
			Point point = shot.getKey();
			file.println(point.x + "," + point.y + " " + shot.getValue().getRGB());
		}
		file.println(max_tiros);

		file.close();
	}

	public void readFile(File givenFile) throws IOException {
		BufferedReader file = new BufferedReader(new FileReader(givenFile));
		String line;
		Navio ship;
		
		int vez = Integer.parseInt(file.readLine());
        if (vez == 1) {
            vezJogador = true;
        }
        else {
            vezJogador = false;
        }
		// Leitura dos dados do jogador 1
		namePlayer1 = file.readLine();
		int shipCount1 = Integer.parseInt(file.readLine());
		for (int i = 0; i < shipCount1; i++) {
			line = file.readLine();
			String[] parts = line.split(" ");
			int shipSize = Integer.parseInt(parts[0]);
			ship = new Navio(shipSize);
			for (int j = 1; j < parts.length; j++) {
				String[] coords = parts[j].split(",");
				int x = Integer.parseInt(coords[0]);
				int y = Integer.parseInt(coords[1]);
				ship.adicionarCoordenada(new Point(x, y));
			}
			ships1.add(ship);
		}
		navios_restantes1 = Integer.parseInt(file.readLine());
		System.out.println(navios_restantes1);
		int shotCount1 = Integer.parseInt(file.readLine());
		for (int i = 0; i < shotCount1; i++) {
			line = file.readLine();
			String[] parts = line.split(" ");
			String[] coords = parts[0].split(",");
			int x = Integer.parseInt(coords[0]);
			int y = Integer.parseInt(coords[1]);
			int rgb = Integer.parseInt(parts[1]);
			shots1.put(new Point(x, y), new Color(rgb));
		}

		// Leitura dos dados do jogador 2
		namePlayer2 = file.readLine();
		int shipCount2 = Integer.parseInt(file.readLine());
		for (int i = 0; i < shipCount2; i++) {
			line = file.readLine();
			String[] parts = line.split(" ");
			int shipSize = Integer.parseInt(parts[0]);
			ship = new Navio(shipSize);
			for (int j = 1; j < parts.length; j++) {
				String[] coords = parts[j].split(",");
				int x = Integer.parseInt(coords[0]);
				int y = Integer.parseInt(coords[1]);
				ship.adicionarCoordenada(new Point(x, y));
			}
			ships2.add(ship);
		}
		navios_restantes2 = Integer.parseInt(file.readLine());
		int shotCount2 = Integer.parseInt(file.readLine());
		for (int i = 0; i < shotCount2; i++) {
			line = file.readLine();
			String[] parts = line.split(" ");
			String[] coords = parts[0].split(",");
			int x = Integer.parseInt(coords[0]);
			int y = Integer.parseInt(coords[1]);
			int rgb = Integer.parseInt(parts[1]);
			shots2.put(new Point(x, y), new Color(rgb));
		}
		max_tiros = Integer.parseInt(file.readLine());

		file.close();
	}

	public File getSelectedFile() {
		return selectedFile;
	}

	public String getNamePlayer1() {
		return namePlayer1;
	}

	public String getNamePlayer2() {
		return namePlayer2;
	}

	public List<Navio> getShips1() {
		return ships1;
	}

	public List<Navio> getShips2() {
		return ships2;
	}

	public Map<Point, Color> getShots1() {
		return shots1;
	}

	public Map<Point, Color> getShots2() {
		return shots2;
	}
	
	public int getNavios_restantes1() {
		return navios_restantes1;
	}

	public int getNavios_restantes2() {
		return navios_restantes2;
	}
	
	public int getMaxTiros() {
		return max_tiros;
	}
	
	public boolean getVezJogador() {
		return vezJogador;
	}
	
}
