import java.util.Scanner;
class Node {
    protected int id;
    protected String nombre;
    protected String descripcion;
    protected Node next;

    //Constructor
    public Node(int m, String d, String l, Node n) {
        id = m;
        nombre = d;
        descripcion = l;
        next = n;
    }

    // Funcion para establecer el nodo siguiente

    public void setLinkNext(Node n) {
        next = n;
    }

    // Funcion para traer link del nodo siguiente

    public Node getLinkNext() {
        return next;
    }
    // Funcion para traer los datos del nodo
    public String getData() {
        return descripcion;
    }
    // Funcion para retornar el id
    public int getId() {
        return id;
    }
    public String getName() {
        return nombre;
    }
    public void nuevosdatos(int newid, String newname, String newdata) {
        id = newid;
        nombre = newname;
        descripcion = newdata;
    }

}
class linkedList {

    protected Node head;
    protected Node tail;
    public int size;

    /* Constructor */
    public linkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void countSize(){
        Node ptr = head;
        size = 0;
        if (head.getLinkNext() == null){
            size = 1;
            return;
        }
        while (ptr.getLinkNext() != null){
            size++;
            ptr = ptr.getLinkNext();
        }
        size++;
    }
    // Funcion para insertar elemento al final
    public void add(int id, String val, String dta) {
        Node nptr = new Node(id, val, dta,null);

        if (head == null) {
            head = nptr;
            tail = head;
        } else {
            tail.setLinkNext(nptr);
            tail = nptr;
        }
        size++;
    }

    public void swap(Node nodo) {
        int id1 = nodo.getId();
        String name1 = nodo.getName();
        String data1 = nodo.getData();
        int id2 = nodo.getLinkNext().getId();
        String name2 = nodo.getLinkNext().getName();
        String data2 = nodo.getLinkNext().getData();

        nodo.nuevosdatos(id2,name2,data2);
        nodo.getLinkNext().nuevosdatos(id1,name1,data1);
    }

    public void eliminar() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Si desea eliminar un dato               oprima ~ U ~");
        System.out.println("Si desea eliminar un conjunto de datos  oprima ~ C ~");
        System.out.println("si desea eliminar todos los datos       oprima ~ T ~");

        String i = scan.next();
        i = i.toLowerCase();

        if (size == 0) {
            System.out.print("            empty\n");
            return;
        }

        if (head.getLinkNext() == null) {
            head = null;
            tail = null;
            return;
        }

        if ("t".equals(i)){
            head = null;
            tail = null;
            size = 0;
            System.out.println("Se elimino con exito...");
            return;
        }
        System.out.print("Ingrese el ID que desea eliminar: ");
        int m = 0;

        try {
            m = scan.nextInt();
        } catch (Exception e){
            System.out.println("Dato ingresado invalido \nIntente de nuevo...\n");
            eliminar();
        }

        Node ptr = head;

        switch (i) {
            case "u" -> {
                if (ptr.getId() == m) {
                    head = ptr.getLinkNext();
                    System.out.println("Se elimino con exito...");
                    size--;
                    return;
                }
                while (ptr.getLinkNext() != null) {
                    if (ptr.getLinkNext().getId() == m) {
                        if (tail == ptr.getLinkNext()) {
                            tail = ptr;
                            ptr.setLinkNext(null);
                            System.out.println("Se elimino con exito...");
                            size--;
                            return;
                        }
                        ptr.setLinkNext(ptr.getLinkNext().getLinkNext());
                        System.out.println("Se elimino con exito...");
                        size--;
                        return;
                    }
                    ptr = ptr.getLinkNext();
                }
            }
            case "c" -> {
                if (head.getId() == m && tail.getId() == m) {
                    head = null;
                    tail = null;
                    size = 0;
                    System.out.println("Se elimino con exito...");
                    return;
                }
                if (ptr.getId() == m) {
                    while (ptr.getLinkNext().getId() == m) {
                        ptr = ptr.getLinkNext();
                    }
                    head = ptr;
                    System.out.println("Se elimino con exito...");
                    countSize();
                    return;
                }
                Node cut;
                while (ptr.getLinkNext() != null) {
                    if (ptr.getLinkNext().getId() == m) {
                        if (tail.getId() == m) {
                            tail = ptr;
                            ptr.setLinkNext(null);
                            System.out.println("Se elimino con exito...");
                            countSize();
                            return;
                        }
                        cut = ptr.getLinkNext();
                        while (cut.getId() == m) {
                            cut = cut.getLinkNext();
                        }
                        ptr.setLinkNext(cut);
                        System.out.println("Se elimino con exito...");
                        countSize();
                        return;
                    }
                    ptr = ptr.getLinkNext();
                }
            }
            case "x" -> {
                System.out.println("Hasta Luego...");
                System.exit(0);
            }
            default -> {
                System.out.println("Ingreso invalido, vuelva y intente:");
                eliminar();
            }
        }
        countSize();
    }

    public void bubblesort() {
        Node ptr = head;
        int check = 0;
        if (size <= 2){
            return;
        }
        while (check < size){
            if (ptr.getId() > ptr.getLinkNext().getId()){
                swap(ptr);
                check = 0;
            }
            if (ptr.getLinkNext().getLinkNext() == null) {
                ptr = head;
            } else {
                ptr = ptr.getLinkNext();
            }
            check++;
        }
    }

    // cuenta cuantos tipos de id hay
    public void numids() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Si desea saber el numero de piezas totales          oprima ~ T ~");
        System.out.println("Si desea saber el numero de piezas por conjuntos    oprima ~ C ~");

        String i = scan.next();
        i = i.toLowerCase();

        switch (i) {
            case "t" -> System.out.println("El numero de piezas totales es: " + size);
            case "c" -> {
                Node ptr = head;
                int ids = 1;
                int u = 0;
                while (ptr.getLinkNext() != null) {
                    u++;
                    if (ptr.getId() != ptr.getLinkNext().getId()) {
                        System.out.println("Pieza: " + ptr.getId() + " Total: " + u);
                        u=0;
                        ids++;
                    }
                    ptr = ptr.getLinkNext();
                }
                u++;
                System.out.println("Pieza: " + ptr.getId() + " Total: " + u);
                System.out.println("===================================================");
                System.out.println("El numero de conjuntos de piezas totales es: " + ids);
            }
            case "x" -> {
                System.out.println("Hasta Luego...");
                System.exit(0);
            }
            default -> {
                System.out.println("Ingreso invalido, vuelva y intente:");
                numids();
            }
        }
    }

    // se ingresa los datos a la base da datos
    public void ingresos(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Para agregar un dato individual   oprima ~ I ~");
        System.out.println("Para una lista de datos           oprima ~ L ~");

        String ingreso = scan.next();

        ingreso = ingreso.toLowerCase();

        switch (ingreso) {
            case "i" -> {
                System.out.println("Ingrese el ID:");
                int ingresoID = 0;
                try {
                    ingresoID = scan.nextInt();
                } catch (Exception e) {
                    System.out.println("Dato ingresado invalido \nIntente de nuevo...\n");
                    ingresos();
                }
                System.out.println("Ingrese el nombre:");
                scan.nextLine();
                String ingresoNom = scan.nextLine();
                System.out.println("Ingrese una descripcion corta:");
                String ingresoDes = scan.nextLine();
                add(ingresoID, ingresoNom, ingresoDes);
                System.out.println("Se ingreso la pieza correctamente...");
            }
            case "l" -> {
                System.out.println("Recuerde que el ingreso de la lista es en el siguiente formato:\n");
                System.out.println("     [[ID,Nombre,Descripcion],...,[ID,Nombre,Descripcion]]     \n");
                scan.nextLine();
                String entrada = scan.nextLine();
                int n = entrada.length();
                try {
                    for (int i = 2; i < n; i++) {
                        StringBuilder ident = new StringBuilder();
                        StringBuilder nomb = new StringBuilder();
                        StringBuilder dscrp = new StringBuilder();
                        while (entrada.charAt(i) != ',') {
                            ident.append(entrada.charAt(i));
                            i++;
                        }
                        i++;
                        while (entrada.charAt(i) != ',') {
                            nomb.append(entrada.charAt(i));
                            i++;
                        }
                        i++;
                        while (entrada.charAt(i) != ']') {
                            dscrp.append(entrada.charAt(i));
                            i++;
                        }
                        int id = Integer.parseInt(ident.toString());
                        String nombre = nomb.toString();
                        String descripcion = dscrp.toString();
                        add(id, nombre, descripcion);
                        i += 2;
                    }
                } catch (Exception e){
                    System.out.println("Dato ingresado invalido \nIntente de nuevo...\n");
                    ingresos();
                }
                System.out.println("Se ingresaron las piezas correctamente...");
            }
            case "x" -> {
                System.out.println("Hasta Luego...");
                System.exit(0);
            }
            default -> {
                System.out.println("Ingreso invalido, vuelva y intente:");
                ingresos();
            }
        }
        bubblesort();
    }

    public void display() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Si desea imprimir todos los datos           oprima  ~ T ~");
        System.out.println("Si desea imprimir un solo conjunto de ID's  oprima  ~ U ~");

        String i = scan.next();
        i = i.toLowerCase();

        if (size == 0) {
            System.out.print("            empty\n");
            return;
        }

        if (head.getLinkNext() == null) {
            System.out.println("ID:             "+head.getId());
            System.out.println("Nombre:         "+head.getName());
            System.out.println("Descripcion:    "+head.getData());
            return;
        }

        switch (i) {
            case "t" -> {
                System.out.println("\n      Base de datos: ");

                System.out.println("ID:             " + head.getId());
                System.out.println("Nombre:         " + head.getName());
                System.out.println("Descripcion:    " + head.getData());
                System.out.println("=========================================");
                Node ptr = head.getLinkNext();

                while (ptr.getLinkNext() != null) {
                    System.out.println("ID:             " + ptr.getId());
                    System.out.println("Nombre:         " + ptr.getName());
                    System.out.println("Descripcion:    " + ptr.getData());
                    System.out.println("=========================================");
                    ptr = ptr.getLinkNext();
                }
                System.out.println("ID:             " + ptr.getId());
                System.out.println("Nombre:         " + ptr.getName());
                System.out.println("Descripcion:    " + ptr.getData());
                System.out.println("=========================================");
            }
            case "u" -> {
                System.out.println("Ingrese el ID de la pieza a buscar: ");
                int in = scan.nextInt();
                boolean k = false;
                Node ptr = head;
                while (ptr.getLinkNext() != null) {
                    if (ptr.getId() == in) {
                        System.out.println("ID:             " + ptr.getId());
                        System.out.println("Nombre:         " + ptr.getName());
                        System.out.println("Descripcion:    " + ptr.getData());
                        System.out.println("=========================================");
                        k = true;
                    }
                    ptr = ptr.getLinkNext();
                }
                if (ptr.getId() == in) {
                    System.out.println("ID:             " + ptr.getId());
                    System.out.println("Nombre:         " + ptr.getName());
                    System.out.println("Descripcion:    " + ptr.getData());
                    System.out.println("=========================================");
                    k = true;
                }

                if (!k) {
                    System.out.println("El ID ingresado no se encuentra en la base de datos.");
                }

            }
            case "x" -> {
                System.out.println("Hasta Luego...");
                System.exit(0);
            }
            default -> {
                System.out.println("Ingreso invalido, vuelva y intente:");
                display();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        linkedList list = new linkedList();
        Scanner scan = new Scanner(System.in);
        System.out.println("                Base de datos de LEGO");
        System.out.println("\n");

        System.out.println("Bienvenidos a la Base de datos de sus fichas de LEGO, donde\n" +
                           "podra organizar todo su inventario de piezas.");

        String in = "a";

        while (!"x".equals(in)){
            System.out.println();

            System.out.println("- Para ingresar nuevos datos        oprima  ~ I ~");
            System.out.println("- Para contar el numero de piezas   oprima  ~ N ~");
            System.out.println("- Para imprimir algunos datos       oprima  ~ D ~");
            System.out.println("- Para eliminar algun dato          oprima  ~ E ~");
            System.out.println("- Para salir del sistema            oprima  ~ X ~");

            in = scan.next();
            in = in.toLowerCase();

            switch (in) {
                case "i" -> list.ingresos();
                case "e" -> list.eliminar();
                case "d" -> list.display();
                case "n" -> list.numids();
                case "x" -> System.out.println("Hasta Luego...");
                default -> System.out.println("Ingreso invalido, vuelva y intente:");
            }
        }
    }
}
