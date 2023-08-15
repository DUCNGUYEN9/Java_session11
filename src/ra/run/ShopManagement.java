package ra.run;

import ra.entity.Categories;
import ra.entity.Product;

import java.util.*;

public class ShopManagement {
    public static List<Categories> categoriesList = new ArrayList<>();
    public static List<Product> productList = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.println("******************SHOP MANAGEMENT***************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choiceShop = Integer.parseInt(scanner.nextLine());
            switch (choiceShop) {
                case 1:
                    ShopManagement.catalogMenu();
                    break;
                case 2:
                    if (categoriesList.size() > 0) {
                        ShopManagement.productMenu();
                    } else {
                        System.err.println("Bạn phải thêm Catalog trước khi thêm product");
                    }
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập trong khoảng 1 ~ 3");
            }
        } while (true);

    }

    //Catalog
    public static void catalogMenu() {
        boolean isExit = false;
        do {
            System.out.println("***************** CATALOG MANAGEMENT**************\n" +
                    "1. Thêm mới danh mục\n" +
                    "2. Hiển thị thông tin các danh mục\n" +
                    "3. Cập nhật tên danh mục theo mã danh mục\n" +
                    "4. Xóa danh mục theo mã danh mục\n" +
                    "5. Thoát (Quay lại Shop Management)\n");
            System.out.print("Lựa chọn của bạn: ");
            int choiceCatalog = Integer.parseInt(scanner.nextLine());
            switch (choiceCatalog) {
                case 1:
                    inputCatalog();
                    break;
                case 2:
                    displayCatalog();
                    break;
                case 3:
                    updateCatalog();
                    break;
                case 4:
                    deleteCatalog();
                    break;
                case 5:
                    isExit = true;
                    break;
                default:
                    System.err.println("Vui lòng nhập trong khoảng 1 ~ 5");

            }
        } while (!isExit);
    }

    public static void inputCatalog() {
        System.out.print("Bạn muốn thêm bao nhiêu catalog: ");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            Categories categories = new Categories();
            categories.inputData();
            categoriesList.add(categories);
        }
    }

    public static void displayCatalog() {
        for (Categories categories : categoriesList) {
            categories.displayData();
        }
    }

    public static void updateCatalog() {
        System.out.print("Nhập mã catalog bạn muốn cập nhật: ");
        int editId = Integer.parseInt(scanner.nextLine());
        boolean isExist = false;
        if (categoriesList.size() > 0) {
            for (Categories categories : categoriesList) {
                if (categories.getCatalogId() == editId) {
                    System.out.println("Nhập vào tên catalog bạn muốn cập nhật: ");
                    categories.setCatalogName(scanner.nextLine());
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                System.out.println("Mã catalog không tồn tại!");
            }
        } else {
            System.out.println("Trong catalog không có danh mục!");
        }
    }

    public static void deleteCatalog() {
        System.out.print("Nhập mã catalog bạn muốn xóa: ");
        int deleteId = Integer.parseInt(scanner.nextLine());
        boolean catalogExistsInProducts = false;
        boolean catalogFound = false;
        for (int i = 0; i < categoriesList.size(); i++) {
            if (categoriesList.get(i).getCatalogId() == deleteId) {
                catalogFound = true;
                if (productList.size() != 0) {
                    for (Product product : productList) {
                        if (product.getCatalogId() == deleteId) {
                            catalogExistsInProducts = true;
                            break;
                        }
                    }
                }
                if (!catalogExistsInProducts) {
                    categoriesList.remove(i);
                    System.out.println("Đã xóa xong!");
                } else {
                    System.err.println("Mã catalog này đã nằm trong danh mục sản phẩm không thể xóa");
                }
                break;
            }
        }
        if (!catalogFound) {
            System.out.println("Mã catalog không tồn tại!");
        }
    }
    //end catalog

    //Product
    public static void productMenu() {
        boolean isExit = false;
        do {
            System.out.println("***************** PRODUCT MANAGEMENT**************\n" +
                    "1. Thêm mới sản phẩm (Khi thêm cho phép chọn danh mục sản phẩm \n" +
                    "mà sản phẩm thuộc về)\n" +
                    "2. Hiển thị thông tin sản phẩm\n" +
                    "3. Cập nhật giá sản phẩm theo mã sản phẩm\n" +
                    "4. Xóa sản phẩm theo mã sản phẩm\n" +
                    "5. Sắp xếp sản phẩm theo giá sản phẩm tăng dần\n" +
                    "6. Sắp xếp sản phẩm theo tên tăng dần\n" +
                    "7. Thống kê số lượng sản phẩm theo danh mục sản phẩm\n" +
                    "8. Tìm kiếm sản phẩm theo tên sản phẩm\n" +
                    "9. Thoát (Quay lại Shop Management)\n");
            System.out.print("Lựa chọn của bạn: ");
            int choiceProduct = Integer.parseInt(scanner.nextLine());
            switch (choiceProduct) {
                case 1:
                    inputProduct();
                    break;
                case 2:
                    displayProduct();
                    break;
                case 3:
                    updatePrice();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    sortPriceASC();
                    break;
                case 6:
                    sortNameASC();
                    break;
                case 7:
                    statisticsToCatalog();
                    break;
                case 8:
                    searchNameProduct();
                    break;
                case 9:
                    isExit = true;
                    break;
                default:
                    System.err.println("Vui lòng nhập trong khoảng 1 ~ 9");

            }
        } while (!isExit);
    }

    public static void inputProduct() {
        System.out.print("Bạn muốn thêm bao nhiêu product: ");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            Product product = new Product();
            product.inputData();
            productList.add(product);
        }
    }

    public static void displayProduct() {
        for (Product product : productList) {
            product.displayData();
        }
    }

    public static void updatePrice() {
        System.out.println("Hãy nhập mã product bạn muốn cập nhật giá: ");
        String productID = scanner.nextLine();
        boolean isExist = true;
        for (Product product : productList) {
            if (product.getProductId().equals(productID)) {
                System.out.println("Hãy nhập giá bạn muốn cập nhật : ");
                product.setPrice(Float.parseFloat(scanner.nextLine()));
            } else {
                isExist = false;
            }
        }
        if (!isExist) {
            System.out.println("Mã product không tồn tại!");
        }
    }

    public static void deleteProduct() {
        System.out.print("Nhập mã product bạn muốn xóa: ");
        String deleteId = scanner.nextLine();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId().equals(deleteId)) {
                productList.remove(i);
                System.out.println("Đã xóa xong!");
                break;
            }
        }
    }

    public static void sortPriceASC() {
        productList.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return Float.compare(o1.getPrice(), o2.getPrice());
            }
        });
        System.out.println("Đã sắp xếp xong!");
    }

    public static void sortNameASC() {
        productList.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getProductName().compareTo(o2.getProductName());
            }
        });
        System.out.println("Đã sắp xếp xong!");
    }

    public static void statisticsToCatalog() {
        int[] arrProduct = new int[productList.size()];
        int num = 0;
        for (int i = 0; i < productList.size(); i++) {
            boolean exists = false;
            for (int j = 0; j < num; j++) {
                if (productList.get(i).getCatalogId() == arrProduct[j]) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                arrProduct[num] = productList.get(i).getCatalogId();
                num++;
            }
        }
        for (int i = 0; i < num; i++) {
            int cnt = 0;
            for (int j = 0; j < productList.size(); j++) {
                if (arrProduct[i] == productList.get(j).getCatalogId()) {
                    cnt++;
                }
            }
            System.out.printf("Mã catalog: %d có %d sản phẩm\n", arrProduct[i], cnt);
        }
    }

    public static void searchNameProduct() {
        System.out.println("Nhập tên bạn muốn tìm: ");
        String nameProduct = scanner.nextLine();
        for (Product pr : productList) {
            if (pr.getProductName().toLowerCase().contains(nameProduct.toLowerCase())) {
                pr.displayData();
            }
        }

    }
    //end product
}
