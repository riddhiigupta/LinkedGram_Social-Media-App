import javax.swing.*;

/**
 * A program Team Project
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project-- </p>
 *
 * @author Purdue CS
 * @version Mar 31, 2024
 */
public class LongRunningTask extends SwingWorker<Void, Void> {
    private JFrame frame;

    public LongRunningTask(JFrame frame) {
        this.frame = frame;
    }

    @Override
    protected Void doInBackground() throws Exception {
        // 执行耗时操作，例如网络请求或复杂计算
        Thread.sleep(5000);  // 假设这是一个耗时的操作
        return null;
    }

    @Override
    protected void done() {
        try {
            get();  // 调用 get() 检查 doInBackground() 中是否有异常抛出
            // 耗时操作完成后，更新或打开新的 GUI 界面
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "操作失败: " + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}
