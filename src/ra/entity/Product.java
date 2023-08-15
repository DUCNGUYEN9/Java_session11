package ra.entity;

import ra.IShop;
import ra.run.ShopManagement;

import static ra.run.ShopManagement.*;

public class Product implements IShop {
    private String productId;
    private String productName;
    private String title;
    private float price;
    private int catalogId;
    private boolean status;

    private String catalogName;

    public Product() {
    }

    public Product(String productId, String productName, String title, float price, int catalogId) {
        this.productId = productId;
        this.productName = productName;
        this.title = title;
        this.price = price;
        this.catalogId = catalogId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData() {
        System.out.println("Nhập vào mã product: ");
        do {
            String checkId = scanner.nextLine();
            if (!checkProductId(checkId)) {
                if (checkId.startsWith("P") && checkId.length() == 5) {
                    this.productId = checkId;
                    break;
                } else {
                    System.err.println("Mã product phải bắt đầu bằng 'P' và có 5 kí tư!");
                }
            } else {
                System.err.println("Mã product bạn vừa nhập đã tồn tại!");
            }
        } while (true);

        System.out.println("Nhập vào tên product: ");
        do {
            String checkName = scanner.nextLine();
            if (!checkProductName(checkName)) {
                this.productName = checkName;
                break;
            } else {
                System.err.println("Ten product bạn vừa nhập đã tồn tại!");
            }
        } while (true);
        System.out.println("***Danh sách Catalog***");
        choiceCatalog();
        System.out.println("Nhập vào giá product: ");
        this.price = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập vào tiêu đề product: ");
        this.title = scanner.nextLine();
        System.out.println("Nhập vào trạng thái product: ");
        this.status = Boolean.parseBoolean(scanner.nextLine());

    }

    boolean checkProductId(String productId) {
        for (Product ca : productList) {
            if (ca.getProductId().equals(productId)) {
                return true;
            }
        }
        return false;
    }

    boolean checkProductName(String productName) {
        for (Product na : productList) {
            if (na.getProductName().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    void choiceCatalog() {
        for (int i = 0; i < categoriesList.size(); i++) {
            System.out.printf("%d: %s\n", i + 1, categoriesList.get(i).getCatalogName());
        }
        System.out.println("Hãy chọn tên catalog: ");
        int choice = Integer.parseInt(scanner.nextLine());
        this.catalogId = categoriesList.get(choice - 1).getCatalogId();
        //thêm tên của danh mục để người dùng dễ đọc
        this.catalogName = categoriesList.get(choice - 1).getCatalogName();
    }

    @Override
    public void displayData() {
        System.out.printf("Mã product: %s -- Tên product: %s -- Trạng Thái: %s\n", productId, productName, status ? "Active" : "inActive");
        System.out.printf("giá: %f -- tiêu đề: %s -- CatalogName: %s\n", price, title,catalogName);
        System.out.println("¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤");

    }
}
