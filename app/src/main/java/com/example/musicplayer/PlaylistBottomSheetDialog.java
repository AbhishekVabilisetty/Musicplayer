    package com.example.musicplayer;

    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;

    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

    import java.util.List;

    public class PlaylistBottomSheetDialog extends BottomSheetDialogFragment {

        private List<String> songs;
        private OnSongClickListener listener;

        // Constructor to initialize songs and the listener
        public PlaylistBottomSheetDialog(List<String> songs, OnSongClickListener listener) {
            this.songs = songs;
            this.listener = listener;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.layout_bottom_sheet, container, false);

            RecyclerView recyclerView = view.findViewById(R.id.recyclerViewSongs);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new SongsAdapter(songs, listener));

            return view;
        }

        // Interface to handle song click events
        public interface OnSongClickListener {
            void onSongClick(int songName);
        }
    }

