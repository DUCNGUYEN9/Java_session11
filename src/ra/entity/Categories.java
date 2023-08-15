package ra.entity;

import ra.IShop;

import static ra.run.ShopManagement.categoriesList;
import static ra.run.ShopManagement.scanner;

public class Categories implements IShop {
    private int catalogId;
    private String catalogName;
    private boolean status;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, boolean status) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.status = status;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData() {
        System.out.println("Nhập vào mã catalog: ");
        do {
            int checkId = Integer.parseInt(scanner.nextLine());
            if (!checkCatalogId(checkId)) {
                this.catalogId = checkId;
                break;
            }else {
                System.err.println("Mã catalog bạn vừa nhập đã tồn tại!");
            }
        } while (true);

        System.out.println("Nhập vào tên catalog: ");
        do {
            String checkName = scanner.nextLine();
            if (!checkCatalogName(checkName)) {
                this.catalogName = checkName;
                break;
            }else {
                System.err.println("Ten catalog bạn vừa nhập đã tồn tại!");
            }
        } while (true);
        System.out.println("Nhập vào trạng  catalog: ");
        this.status = Boolean.parseBoolean(scanner.nextLine());

    }

    boolean checkCatalogId(int catalogId) {
        for (Categories ca : categoriesList) {
            if (ca.getCatalogId() == catalogId) {
                return true;
            }
        }
        return false;
    }
    boolean checkCatalogName(String catalogName) {
        for (Categories na : categoriesList) {
            if (na.getCatalogName().equals(catalogName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void displayData() {
        System.out.printf("Mã catalog: %d -- Tên catalog: %s -- Trạng Thái: %s\n", catalogId, catalogName, status ? "Active" : "inActive");
        System.out.println("¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤");
    }
}
