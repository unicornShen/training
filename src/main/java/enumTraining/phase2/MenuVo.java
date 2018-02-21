package enumTraining.phase2;

public class MenuVo {
    private String menuLabel;

    private String menuValue;

    public MenuVo() {
        super();
    }

    public MenuVo(final String menuValue, final String menuLabel) {
        this.menuLabel = menuLabel;
        this.menuValue = menuValue;
    }

    public String getMenuLabel() {
        return this.menuLabel;
    }

    public void setMenuLabel(final String menuLabel) {
        this.menuLabel = menuLabel;
    }

    public String getMenuValue() {
        return this.menuValue;
    }

    public void setMenuValue(final String menuValue) {
        this.menuValue = menuValue;
    }
}
