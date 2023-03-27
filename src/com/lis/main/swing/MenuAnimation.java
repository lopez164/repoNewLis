package com.lis.main.swing;

import java.awt.Component;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author lelopez
 */
public class MenuAnimation {

    private final MigLayout layout;
    private final MenuItem menuItem;
    private Animator animator;
    private boolean open;

    public MenuAnimation(MigLayout layout, Component component) {
        this.layout = layout;
        this.menuItem = (MenuItem) component;
        initAnimator(component, 200);

    }

    public MenuAnimation(MigLayout layout, Component component, int duration) {
        this.layout = layout;
        this.menuItem = (MenuItem) component;
        initAnimator(component, duration);

    }

    private void initAnimator(Component comp, int duration) {
        int height = comp.getPreferredSize().height;
        TimingTarget target = new TimingTargetAdapter() {

            @Override
            public void timingEvent(float fraction) {

                float h;
                if (open) {
                    h = 30 + ((height - 30) * fraction);
                    menuItem.setAlpha(fraction);
                } else {
                    h = 30 + ((height - 30) * (1f - fraction));
                    menuItem.setAlpha(1f - fraction);
                }
                layout.setComponentConstraints(menuItem, "h " + h + "!");
                comp.revalidate();
                comp.repaint();

            }

        };
        animator = new Animator(duration, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);

    }

    public void openMenu() {
        open = true;
        animator.start();
    }

    public void closeMenu() {
        open = false;
        animator.start();
    }

}
