package hr.system.dtos;

public class Credentials {
    private boolean isRightCredentials;
    private boolean isManager = false;

    public Credentials(boolean isRightCredentials, boolean isManager) {
       this.isRightCredentials = isRightCredentials;
       this.isManager = isManager;
    }

    public boolean isRightCredentials() {
        return isRightCredentials;
    }

    public void setRightCredentials(boolean rightCredentials) {
        isRightCredentials = rightCredentials;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }
}
