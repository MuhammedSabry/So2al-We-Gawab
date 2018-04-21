package com.sabry.muhammed.qanda.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sabry.muhammed.qanda.R;
import com.sabry.muhammed.qanda.model.Question;

import java.util.List;


public class QuestionsRecyclerAdapter extends android.support.v7.widget.RecyclerView.Adapter<QuestionsRecyclerAdapter.QuestionsViewHolder> {

    private List<Question> list;
    private OnItemClickListener mOnClick;

    public QuestionsRecyclerAdapter(List<Question> qList, OnItemClickListener listener) {
        this.list = qList;
        this.mOnClick = listener;
    }

    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.questions_recycler_item
                        , parent
                        , false);
        return new QuestionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {
        Question question = list.get(position);
        holder.bind(question);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void OnClick(View view, int position);

        boolean OnLongClickListener(View view, int position);
    }

    class QuestionsViewHolder extends RecyclerView.ViewHolder {

        TextView question;
        TextView topAnswer;
        ConstraintLayout layout;

        QuestionsViewHolder(View view) {
            super(view);
            this.question = view.findViewById(R.id.question);
            this.topAnswer = view.findViewById(R.id.first_answer);
            this.layout = view.findViewById(R.id.item);

            this.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClick.OnClick(v, getAdapterPosition());
                }
            });
            this.layout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mOnClick.OnLongClickListener(v, getAdapterPosition());
                }
            });
        }

        void bind(Question q) {
            question.setText(q.getQuestion());
            if (q.getAnswers() != null)
                topAnswer.setText(q.getAnswers().get(0).getText());
            else
                topAnswer.setText("No answers yet :(");
        }
    }
}
