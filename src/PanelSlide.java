
import com.conf.Inicio;
import com.lis.main.Main;
import com.obj.CUsuarios;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import java.io.File;
import static java.lang.Math.pow;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class PanelSlide extends javax.swing.JLayeredPane {

    private final Animator animator;
    private float animate = 1f;
    private boolean slideLeft;
    private final PanelLogin login;
    private final PanelLoading loading;
    private Thread th;
    private Inicio ini;
    private MigLayout layout;
    private JFrame frame;

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Creates new form PanelSlide
     */
    public PanelSlide() {

        initComponents();
        this.ini = new Inicio();

        setPreferredSize(new Dimension(350, 450));
        layout = new MigLayout("Inset 0", "[fill]", "[fill]");
        setLayout(layout);
        login = new PanelLogin();
        loading = new PanelLoading();
        loading.setVisible(false);
        Color color = ini.getColorBackGround();

        setBackground(color);
        loading.setBackground(color);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (slideLeft) {
                    loading.setVisible(true);
                } else {
                    login.setVisible(true);
                }

            }

            @Override
            public void timingEvent(float f) {
                double width = getWidth();
                animate = f;
                float a = easeOutQuint(f);
                int x = (int) (a * width);
                loading.setAnimate(slideLeft, f);
                layout.setComponentConstraints(loading, "pos " + x + " 0 100% 100%");
                revalidate();
                repaint();
            }

            @Override
            public void end() {
                if (slideLeft) {
                    login.setVisible(false);
                } else {
                    loading.setVisible(false);
                    loading.reset();
                }
            }

        };
        animator = new Animator(1000, target);
        animator.setResolution(0);
        add(loading, "pos 0  0  0  0 , w 0!");

        loading.addEventBack(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                th.interrupt();
                showSlide(false);

            }
        });
        loading.addEventContinue(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Main main = new Main(); // es el main del login
                // main.setData(loading.getData());
                //  main.setVisible(true);
                //frame.dispose();
            }
        });
        add(login);
        login.addEventLogin(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!animator.isRunning()) {
                    if (login.checkUser()) {
                        showSlide(true);
                        login(login.getUserName(), login.getPassword());
                    }

                }

            }

        });

    }

    public void setIni(Inicio ini) {
        this.ini = ini;
    }

    public void showSlide(boolean show) {
        slideLeft = show;
        animator.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.CardLayout());
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates
        if (slideLeft == false) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = getWidth();
            int height = getHeight();
            float x = easeOutQuint(animate) * width;
            float y = 0;
            int centerY = height / 2;
            Path2D.Float p = new Path2D.Float();
            p.moveTo(x, y);
            p.lineTo(x, height);
            p.curveTo(x, height, easeOutBounce(animate) * width, centerY, x, y);
            g2.setColor(getBackground());
            g2.fill(p);
            g2.dispose();
        }

    }

    private float easeOutBounce(float x) {
        float n1 = 7.5625f;
        float d1 = 2.75f;

        double v;

        if (x < 1 / d1) {
            v = n1 * x * x;
        } else if (x < 2 / d1) {
            v = n1 * (x -= 1.5 / d1) * x + 0.75;
        } else if (x < 2.5 / d1) {
            v = n1 * (x -= 2.25 / d1) * x + 0.9375;
        } else {
            v = n1 * (x -= 2.625 / d1) * x + 0.984375;
        }
        if (slideLeft) {
            return (float) (1f - v);
        } else {
            return (float) v;
        }

    }

    private float easeOutQuint(float x) {
        double v;
        v = 1 - pow(1 - x, 5);

        if (slideLeft) {
            return (float) (1f - v);
        } else {
            return (float) v;
        }
    }

    private void login(String user, String pass) {
        th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    CUsuarios usuario = new CUsuarios(ini);

                    if (true) {

                        if (usuario.comparar(user, pass)) {
                            Thread.sleep(2000);

                            //loading.doneLogin(new Model_user(1, "luis", new ImageIcon(getClass().getResource("/com/lis/icon/p1.jpg"))));
                            Main app = new Main(ini);
                            app.show();
                            app.setVisible(true);
                            frame.dispose();
                            
                            /*carga las variables del sistema*/
                            ini = new Inicio(new File("ReadUs.ini"));
                           

                        } else {
                            Thread.sleep(2000);
                            loading.showMessageErro("Nombre de Usuario o clave incorrecta ");
                        }
                    } else {
                        Thread.sleep(2000);
                        loading.showMessageErro("Cliente no esta activo");
                    }

                } catch (InterruptedException e) {

                } catch (Exception e) {
                    loading.showMessageErro("Error server" + e);
                }
            }
        });
        th.start();
    }

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
