
class FileSystemItem {
    String name;
    String location;
    Directory parent;

    public void setLocationFromParent() {
        this.location = this.parent.location + this.name + "\\";
    }
}

class Directory extends FileSystemItem {
    Directory[] innerDirectory;
    File innerFiles[];

    public Directory(String name) {
        super.name = name;
        innerDirectory = new Directory[10];
        innerFiles = new File[10];

    }

    public static Directory openCommandPrompt() {
        Directory root = new Directory("~");
        root.innerDirectory[0] = new Directory("c:\\");
        root = root.innerDirectory[0];
        root.location = "c:\\";
        return root;
    }

    public void excutePresentWorkingDiretory() {
        System.out.println(this.location);
    }

    public Directory excuteChangeDir(String directoryName) {
        // check is give directory is in the innerDirectory array
        if (directoryName.equals("..")) {
            return excuteMoveBack();

        } else if (directoryName.equals("//")) {
            return excuteHome();
        }
        return getInnerDirectory(directoryName);

    }

    private Directory getInnerDirectory(String directoryName) {
        for (Directory directory : innerDirectory) {
            if (directory != null && directory.name.equals(directoryName)) {
                return directory;
            }
        }
        System.out.println("Directory not found");
        return this;
    }

    public void excuteMakeDir(String directoryName) {
        // create directory object with the given name
        Directory directory = new Directory(directoryName);
        directory.location = this.location + directoryName + "\\";
        directory.parent = this;
        this.innerDirectory[getDirectoryLength()] = directory;
        System.out.println("directory " + directory.name + " has been created");
    }

    private int getDirectoryLength() {
        int count = 0;
        for (Directory directory : innerDirectory) {
            if (directory == null) {
                return count;
            }
            count++;
        }
        return count;
    }

    private Directory excuteMoveBack() {
        return this.parent;
    }

    public void excuteTouch(String fileName) {
        File file = new File(fileName);
        file.location = this.location + fileName + "\\";
        file.parent = this;
        this.innerFiles[getFilesLength()] = file;
    }

    private int getFilesLength() {
        int count = 0;
        for (File file : this.innerFiles) {
            if (file == null) {
                return count;
            }
            count++;
        }
        return count;
    }

    public void excuteListDir() {
        for (Directory directory : this.innerDirectory) {
            if (directory != null) {
                System.out.println(directory.location);
            }
        }
        for (File file : this.innerFiles) {
            if (file != null) {
                System.out.println(file.location);
            }
        }

    }

    public Directory excuteHome() {
        Directory currentDirectory = this;
        while (!currentDirectory.name.equals("c:\\")) {
            currentDirectory = currentDirectory.parent;
        }
        return currentDirectory;
    }

    public void excuteRename(String newName) {
        // change the current name to new Name
        this.name = newName;
        this.setLocationFromParent();
        this.changeChildLocation();

    }

    private void changeChildLocation() {
        for (Directory childDirectory : this.innerDirectory) {
            if (childDirectory == null) {
                return;
            }
            childDirectory.setLocationFromParent();//location 
            childDirectory.changeChildLocation();
            childDirectory.changeInnerFilesLocation();
        }

    }

    private void changeInnerFilesLocation() {
        for (File file : this.innerFiles) {
            if (file == null) {
                break;
            }
            file.setLocationFromParent();

        }
    }

    public void excuteRenameFile(String currentFileName, String newFileName) {
        File file = getInnerFile(currentFileName);
        file.name = newFileName;
        file.setLocationFromParent();
    }

    private File getInnerFile(String currentFileName) {
        for (File file : this.innerFiles) {
            if (file != null && file.name.equals(currentFileName)) {
                return file;
            }
        }
        return null;
    }

    public void excuteNano(String fileName, String content) {
        File file = getNewOrOldFile(fileName);

    }

    private File getNewOrOldFile(String fileName) {
        File file = getInnerFile(fileName);
        if (file == null) {
            excuteTouch(fileName);
            file = this.innerFiles[getFilesLength() - 1];

        }
        return file;
    }

    public void excuteCat(String fileName) {

    }

}

class File extends FileSystemItem {
    String[] FileSystemItem;

    public File(String fileName) {
        this.name = fileName;
    }

}

public class PlayWithDirectory {

    public static void main(String[] args) {
        System.out.println();
        System.out.println();

        Directory root = Directory.openCommandPrompt();
        normalTest(root);
        reNameTesting(root);
    }

    private static void normalTest(Directory root) {
        root.excutePresentWorkingDiretory();

        root.excuteMakeDir("wild Animal");
        root.excuteChangeDir("..");
        root.excuteTouch("fileName");
        root.excuteListDir();

        root.excuteMakeDir("animal");
        root.excuteMakeDir("bird");
        root = root.excuteChangeDir("bird");
        root.excuteMakeDir("mountainBirds");
        root.excuteMakeDir("domestic birds");
        root = root.excuteChangeDir("mountainBirds");
        root.excuteMakeDir("pretator");
        root = root.excuteChangeDir("pretator");

        root = root.excuteChangeDir("..");
        root = root.excuteChangeDir("..");
        root.excuteRename("bird advance");
        root = root.excuteChangeDir("mountainBirds");
        root.excuteRename("mountainBirdsAdvance");
        root = root.excuteChangeDir("pretator");
        root.excutePresentWorkingDiretory();

        root = root.excuteChangeDir("snaks");
        root = root.excuteChangeDir("animal");
        root.excuteTouch("a.py");
        root.excutePresentWorkingDiretory();
        root.excuteListDir();

        root = root.excuteHome();
        root.excutePresentWorkingDiretory();
    }

    private static void reNameTesting(Directory root) {
        root.excuteMakeDir("challenge");
        root = root.excuteChangeDir("challenge");

        root.excuteMakeDir("100-day-challenge");
        root.excuteMakeDir("chatApp");
        root.excuteMakeDir("designPattern");
        root.excuteMakeDir("designPattern");
        root.excuteMakeDir("Iot");
        root = root.excuteChangeDir("100-day-challenge");
        root.excuteMakeDir("zoho-day-1");
        root.excuteMakeDir("zoho-day-2");
        root = root.excuteChangeDir("zoho-day-1");
        root.excuteTouch("a.java");
        root.excuteTouch("b.java");
        root.excuteTouch("c.java");
        root.excuteListDir();
        root = root.excuteChangeDir("..");
        root = root.excuteChangeDir("..");

        root.excuteRename("challengeAdvance");
        System.out.println("after rename");
        root = root.excuteChangeDir("100-day-challenge");
        // System.out.println(root.location);
        root = root.excuteChangeDir("zoho-day-1");
        // root.excutePresentWorkingDiretory();
        root.excuteListDir();
        root.excuteRenameFile("a.java", "aa.java");
        System.out.println("after file rename");
        root.excuteListDir();
        root.excuteNano("aa.java", "vanakkam");
        root.excuteCat("aa.java");

    }

}
