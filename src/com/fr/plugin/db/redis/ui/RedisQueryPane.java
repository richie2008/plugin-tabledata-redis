package com.fr.plugin.db.redis.ui;

import com.fr.design.border.UIRoundedBorder;
import com.fr.design.constants.UIConstants;
import com.fr.design.data.datapane.sqlpane.SQLEditPane;
import com.fr.design.dialog.BasicPane;
import com.fr.design.gui.icheckbox.UICheckBox;
import com.fr.design.gui.icombobox.UIComboBox;
import com.fr.design.gui.ilable.ActionLabel;
import com.fr.design.gui.ilable.UILabel;
import com.fr.design.gui.itextfield.UITextField;
import com.fr.design.gui.syntax.ui.rsyntaxtextarea.SyntaxConstants;
import com.fr.design.gui.syntax.ui.rtextarea.RTextScrollPane;
import com.fr.design.layout.TableLayout;
import com.fr.design.layout.TableLayoutHelper;
import com.fr.design.utils.gui.GUICoreUtils;
import com.fr.general.FRLogger;
import com.fr.general.GeneralUtils;
import com.fr.general.Inter;
import com.fr.general.SiteCenter;
import com.fr.stable.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;


public class RedisQueryPane extends BasicPane {


    private SQLEditPane sqlTextPane;

    private boolean isFirstTime = true;

    public RedisQueryPane() {
        setLayout(new BorderLayout());
        sqlTextPane = new SQLEditPane();

        JPanel northPane = new JPanel(new BorderLayout());
        add(northPane, BorderLayout.NORTH);

        northPane.add(new UILabel(Inter.getLocText("Plugin-Redis_Query_Condition") + ":"), BorderLayout.WEST);

        ActionLabel helpLabel = new ActionLabel(Inter.getLocText("Plugin-Redis_Help"));
        helpLabel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(URI.create(SiteCenter.getInstance().acquireUrlByKind("help.redis")));
                } catch (IOException e1) {
                    FRLogger.getLogger().error(e1.getMessage(), e1);
                }
            }
        });
        northPane.add(helpLabel, BorderLayout.EAST);

        add(createConditionTextPane(sqlTextPane), BorderLayout.CENTER);
    }

    private RTextScrollPane createConditionTextPane(SQLEditPane sqlTextPane) {
        sqlTextPane.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
        RTextScrollPane sqlTextScrollPane = new RTextScrollPane(sqlTextPane);
        sqlTextScrollPane.setBorder(new UIRoundedBorder(UIConstants.LINE_COLOR, 1, UIConstants.ARC));
        sqlTextScrollPane.setPreferredSize(new Dimension(680, 300));
        return sqlTextScrollPane;
    }

    @Override
    protected String title4PopupWindow() {
        return "Query";
    }

    public void loadDBNames(String[] names) {

    }

    public String getQuery() {
        return sqlTextPane.getText();
    }

    public void setQuery(String query) {
        sqlTextPane.setText(query);
    }

}